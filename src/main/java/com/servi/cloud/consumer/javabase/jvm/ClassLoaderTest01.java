package com.servi.cloud.consumer.javabase.jvm;

import java.util.UUID;

public class ClassLoaderTest01 {

    public static void main(String[] args) {
        //但一个常量的值在编译期间不是确定的，那么其值就不会被放到调用类的常量池当中
        //这时当程序运行时，会导致主动使用这个常量所在的类，导致被调用类被初始化
        System.out.println(Parent1.str1);
    }
}

class Parent1 {
    public static final String str1 = UUID.randomUUID().toString();

    static {
        System.out.println("Parent1 static block ");
    }
}
