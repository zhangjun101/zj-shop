package com.zj.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User u1 = new User("李四", "北京", 20);
        User u2 = new User("张三", "北京", 22);
        User u3 = new User("王五", "上海", 26);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        //取出住在北京的数据
        System.out.println("过滤之前的list:\n" + list);
        List<User> collect = list.stream().filter(u -> "北京".equals(u.getDept())).collect(Collectors.toList());
        System.out.println("取出住在北京的list:\n" + collect);

        //取出所有的人名
        System.out.println("-------我是分割线-------");
        List<String> collect1 = list.stream().map(User::getName).collect(Collectors.toList());
        System.out.println("所有的人名:\n" + collect1);
    }

}
