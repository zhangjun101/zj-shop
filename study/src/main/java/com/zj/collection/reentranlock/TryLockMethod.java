package com.zj.collection.reentranlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Reentranlock的trylock()方法使用
 */
public class TryLockMethod {

    /**
     * 方法 method1 方法同步
     */
    public static void method1(Lock lock, List list) {
        if (lock.tryLock()) {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("当前线程" + Thread.currentThread().getName() + "： i=" + i);
                    list.add(i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("当前线程" + Thread.currentThread().getName() + "没有获取到锁");
        }

    }


    public static class MyThread1 implements Runnable {
        Lock lock = null;
        List list = null;

        public MyThread1(Lock lock, List list) {
            this.lock = lock;
            this.list = list;
        }

        @Override
        public void run() {
            method1(lock, list);
        }
    }


    /**
     * 测试：多个线程同时向Arraylist中添加数据
     * 逻辑：
     * 预期分析：trylock（）锁，
     * 1会判断是否能获取到锁，如果没有获取到锁不会阻塞线程
     * 2.没有获取到锁进行unlock（）操作会异常。
     * 结果：预期一致
     */
    public static void result1() {
        List list = new ArrayList();
        ReentrantLock lock = new ReentrantLock();
        //MyThread2  myThread2 = new MyThread2(lock,list);
        MyThread1 myThread1 = new MyThread1(lock, list);
        new Thread(myThread1, "myThread1").start();
        new Thread(myThread1, "myThread11").start();
    }


    public static void main(String[] args) {
        result1();
    }


}
