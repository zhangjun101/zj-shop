package com.zj.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;


//熟悉Stream Api
public class Test4 {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User u1 = new User("李四", "北京", 20);
        User u2 = new User("张三", "北京", 22);
        User u3 = new User("王五", "上海", 26);
        User u4 = new User("wangwu", "上海", 26);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);


        Object[] toArray = list.toArray();

        //1.创建Stream  Stram是个接口，通常采用已有的方法进行创建
        //常见的有三种方式 1 Collection.stream();2 Collection.parallelStream();3Arrays.stream(T[] array)
        Stream<User> userStream = list.stream();
        Stream<User> parallelStream = list.parallelStream();
        Stream<Object> objectStream = Arrays.stream(toArray);


        System.out.println("==========================================================");
        //2.Stream方法 遍历 void forEach(Consumer<? super E> action)
        //forEach 是结束方法，下面代码会立即执行，输出所有字符串。
        userStream.forEach(user -> System.out.println(user));
//        for (User user: list) { }
//        userStream.forEach(
//            new Consumer<User>() {
//                @Override
//                public void accept(User user) {
//                    System.out.println(user);
//                }
//            }
//        );

        System.out.println("==========================================================");
        //3 中间操作 过滤 Stream<T> filter(Predicate<? super T> predicate)
        Stream<User> userStream0 = list.stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                Boolean a = (user.getAge() == 3);
                boolean b = (user.getName().equals(""));
                return a && b;
            }
        });

        Stream<User> userStream1 = userStream0.filter(user -> {
            Boolean a = (user.getAge() == 3);
            boolean b = (user.getName().equals(""));
            return a && b;
        });

        System.out.println("==========================================================");
        //4.去除重复元素 userStream
        Stream<User> userStream2 = list.stream().distinct();
        userStream2.forEach(str -> System.out.println(str));


        System.out.println("==========================================================");
        //5.排序操作 排序函数有两个，一个是用自然顺序排序，一个是使用自定义比较器排序，函数原型分别为Stream<T>　sorted()
        // 和Stream<T>　sorted(Comparator<? super T> comparator)。
        list.stream().sorted();
        list.stream().sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        list.stream().sorted((o1, o2) -> {
            return o1.getAge() - o2.getAge();
        });

        System.out.println("6==========================================================");
        //6.函数原型为<R> Stream<R> map(Function<? super T,? extends R> mapper)，
        // 作用是返回一个对当前所有元素执行执行mapper之后的结果组成的Stream。
        // 直观的说，就是对每个元素按照某种操作进行转换，转换前后Stream中元素的个数不会改变，
        // 但元素的类型取决于转换之后的类型。
//        list.stream().map(new Function<User, Object>() {
//            @Override
//            public Object apply(User user) {
//                String s = user.getName().toUpperCase();
//                user.setName(s);
//                return user;
//            }
//        });

        list.stream().map(user -> {
            String s = user.getName().toUpperCase();
            user.setName(s);
            return user;
        }).forEach(str -> System.out.println(str));


    }


}
