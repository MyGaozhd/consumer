package com.servi.cloud.consumer.javabase.jvm;

public class ClassLoaderTest05 {
    static {
        System.out.println("ClassLoaderTest05");

    }

    public static void main(String[] args) {
        System.out.println(Child5.b);
    }
}

class Parent5{
    static int a = 5;
    static {
        System.out.println("Parent5");
    }
}

class Child5 extends  Parent5{
    static int b= 4;
    static {
        System.out.println("Child5");
    }
}
