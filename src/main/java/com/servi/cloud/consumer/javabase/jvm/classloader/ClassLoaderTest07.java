package com.servi.cloud.consumer.javabase.jvm.classloader;

import java.util.concurrent.Callable;

public class ClassLoaderTest07 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("\r\n");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("\r\n");
        System.out.println(System.getProperty("java.class.path"));
    }


}
