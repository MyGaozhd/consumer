package com.servi.cloud.consumer.javabase.callerclass;

import sun.reflect.Reflection;

public class T {

    public T() {
        System.out.println(Reflection.getCallerClass(0));
        System.out.println(Reflection.getCallerClass(1));
        System.out.println(Reflection.getCallerClass(2));
        System.out.println(Reflection.getCallerClass(3));
        System.out.println(Reflection.getCallerClass(4));

    }
}
