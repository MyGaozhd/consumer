package com.servi.cloud.consumer.javabase.jvm.classloader;

/**
 * 强制修改系统类加载器
 */
public class SystemClassLoader {

    public static void main(String[] args) {
        //-Djava.system.class.loader=com.servi.cloud.consumer.javabase.jvm.classloader.ServiClassLoader
        System.out.println(System.getProperty("java.system.class.loader"));
        System.out.println(SystemClassLoader.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
