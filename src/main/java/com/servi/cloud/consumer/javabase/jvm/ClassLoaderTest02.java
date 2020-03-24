package com.servi.cloud.consumer.javabase.jvm;

/**
 * 对于数组类型来说，其类型是虚拟机运行使其生成的，表示为class [Lcom.servi.cloud.consumer.javabase.jvm.Parent2;
 *
 * 虚拟机指令参数 anewarray 创建一个数组
 *
 *public class com.servi.cloud.consumer.javabase.jvm.ClassLoaderTest02 {
 *   public com.servi.cloud.consumer.javabase.jvm.ClassLoaderTest02();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: iconst_1
 *        1: anewarray     #2                  // class com/servi/cloud/consumer/javabase/jvm/Parent2
 *        4: astore_1
 *        5: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        8: aload_1
 *        9: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
 *       12: invokevirtual #5                  // Method java/lang/Class.toString:()Ljava/lang/String;
 *       15: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *       18: return
 * }
 */

public class ClassLoaderTest02 {

    public static void main(String[] args) {
        Parent2[] parent2 = new Parent2[1];
        System.out.println(parent2.getClass().toString());
    }
}

class Parent2{

    static {
        System.out.println("Parent1 static block ");
    }
}