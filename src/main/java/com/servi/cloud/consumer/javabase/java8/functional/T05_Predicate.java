package com.servi.cloud.consumer.javabase.java8.functional;

import java.util.function.Predicate;

/**
 * 实现一个Predicate的test方法，根据传入的参数判断是否 true
 */
public class T05_Predicate {

    public static void main(String[] args) {
        Predicate<Integer> p = x -> x < 5;
        System.out.println(p.test(2));
    }
}
