package com.servi.cloud.consumer.javabase.java8.functional;

import java.util.function.Function;

public class T04_Function {

    public static void main(String[] args) {
        System.out.println(T04_Function.oper(x -> (x + 1) + ""));
    }

    public static String oper(Function<Integer, String> f) {
        return f.apply(333);
    }
}
