package com.servi.cloud.consumer.javabase.jvm;

/**
 * 类的加载过程：
 * 加载
 * 连接
 *    验证
 *    准备
 *    解析
 * 初始化
 *
 * 输出结果：
 * 1
 * 1
 * a:1
 * b:0
 * 原因如下：
 * Parent3 在连接的准备阶段
 * a = 0; instance = null; b = 0;
 * 初始化阶段：
 * a = 0; 因为程序本身没有给a 进行赋值
 * instance = new Parent3(); 此时调用Parent3的构造方法 a = 1,b = 1;
 * b = 0; instance初始化之后执行b的初始化，把 b 改为0
 */
public class ClassLoaderTest03 {
    public static void main(String[] args) {
        Parent3 parent3 = Parent3.getInstance();
        System.out.println("a:" + parent3.a);
        System.out.println("b:" + parent3.b);
    }
}

class Parent3 {
    public static int a;
    public static Parent3 instance = new Parent3();
    public static int b = 0;
    public Parent3() {
        a++;
        b++;
        System.out.println(a);
        System.out.println(b);
    }

    public static Parent3 getInstance() {
        return instance;
    }
}