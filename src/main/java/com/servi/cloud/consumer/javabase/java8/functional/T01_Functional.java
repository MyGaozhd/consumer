package com.servi.cloud.consumer.javabase.java8.functional;

/**
 * 用FunctionalInterface注解修饰的接口，只允许存在一个抽象方法，jdk自动校验。只有这个作用
 * 不用FunctionalInterface注解修饰的接口，如果也是只有一个抽象方法，仍然可以使用lamda表达式
 */
@FunctionalInterface
public interface T01_Functional {
    public static void main(String[] args) {
        //lamda 不允许重载default方法
        T01_Functional t = () -> {
            System.out.println("one");
        };

        // 内部类允许重载two方法
        T01_Functional t1 = new T01_Functional() {
            @Override
            public void one() {

            }

            @Override
            public void two() {

            }
        };
        t.one();
        t.two();
    }

    public void one();

    public default void two() {
        System.out.println("two");
    }
}
