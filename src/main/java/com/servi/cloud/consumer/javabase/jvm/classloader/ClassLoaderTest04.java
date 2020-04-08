package com.servi.cloud.consumer.javabase.jvm.classloader;

/**输出结果：
 * 5
 * Parent4_1 初始化
 * 5
 * 原因说明：
 * 当一个类被初始化的时候，他的父类会首先被初始化
 * 当一个类被初始化的时候，他继承的接口不会主动被初始化
 */
public class ClassLoaderTest04 {
    public static void main(String[] args) {
        System.out.println(Child4.a);
        System.out.println(Child4_1.a);
    }
}
interface Parent4 {
    public static Thread t = new Thread() {
        {
            System.out.println("Parent4 初始化");
        }
    };
}
class Child4 implements Parent4 {
    public static int a = 5;
}
class Parent4_1 {
    public static Thread t = new Thread() {
        {
            System.out.println("Parent4_1 初始化");
        }
    };
}
class Child4_1 extends Parent4_1 {
    public static int a = 5;
}