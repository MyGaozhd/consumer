package com.servi.cloud.consumer.javabase.jvm;

public class ClassLoaderTest {
    public static void main(String[] args) {

        //输出结果如下，说明Child子类并没有被加载。只有父类被用到父类被加载。主动适用了Parent，没有主动适用Child。：
       // System.out.println(Child.str);
        //输出结果，此时用到Child的str2字段，Child类被加载，主动使用了Child类，父类也被主动适用，也被加载并初始化。：
        //System.out.println(Child.str2);

        System.out.println(Child.str1);
    }
}

class Parent {
    public static String str ="hello word";
    public static final String str1 ="hello word";

    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent{
    public static String str2 ="hello word";
    static {
        System.out.println("Child static block");
    }
}