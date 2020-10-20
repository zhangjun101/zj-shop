package com.zj.collection.reentranlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Reentranlock的lock()方法使用
 */
public class LockMethod {

    /**
     * 方法 method1 方法同步
     */
    public static void method1(Lock lock, List list) {
        try {
            lock.lock();
            for (int i = 0; i < 100; i++) {
                System.out.println("当前线程" + Thread.currentThread().getName() + "： i=" + i);
                list.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 方法 method2 方法同步
     */
    public static void method2(Lock lock, List list) {
        try {
            lock.lock();
            for (int i = 100; i < 200; i++) {
                System.out.println("当前线程" + Thread.currentThread().getName() + "： i=" + i);
                list.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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

    public static class MyThread2 implements Runnable {
        Lock lock = null;
        List list = null;

        public MyThread2(Lock lock, List list) {
            this.lock = lock;
            this.list = list;
        }

        @Override
        public void run() {
            method2(lock, list);
        }
    }


    /**
     * 测试：多个线程同时向Arraylist中添加数据
     * 逻辑：创建多个myThread1线程同时执行method1方法，method1方法内容加锁
     * 预期分析：同一个lock锁,同时只能一个线程执行，list中数据应该是连续的
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

    /**
     * 测试：多个线程同时向Arraylist中添加数据
     * 逻辑：创建多个线程同时执行method1方法，method1方法内容加锁,但不是同一个lock对象
     * 预期分析：不同的lock锁,同时会有多个线程执行，list中数据不连续，可能出现扩容时产生异常
     * 结果：预期一致
     */
    public static void result2() {
        List list = new ArrayList();
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        MyThread1 myThread1 = new MyThread1(lock, list);
        MyThread1 myThread12 = new MyThread1(lock, list);
        MyThread1 myThread13 = new MyThread1(lock2, list);
        new Thread(myThread1, "myThread1").start();
        new Thread(myThread12, "myThread12").start();
        new Thread(myThread13, "myThread13").start();
    }

    /**
     * 测试3:多个线程操作同一个list
     * 逻辑：
     * 预期分析：Lock 的lock（）方法即使在interrupt（）后也不会释放锁
     * 结果：预期一致
     */
    public static void result3() {

        List list = new ArrayList();
        ReentrantLock lock = new ReentrantLock();
        MyThread1 myThread1 = new MyThread1(lock, list);
        MyThread1 myThread12 = new MyThread1(lock, list);

        Thread thread = new Thread(myThread1, "myThread1");
        thread.start();
        thread.interrupt();//打上中断标志位

        new Thread(myThread12, "myThread12").start();
    }


    public static void main(String[] args) {
        result3();
    }


}
