package com.servi.cloud.consumer.javabase.java8.functional;

import java.util.function.Consumer;

/**
 * Consumer 接口用于消费数据
 */
public class T03_Consumer {

    public static void main(String[] args) {
        new T03_Consumer().one(t -> System.out.println(t));

        new T03_Consumer().two(t -> System.out.println(t));
    }

    void one(Consumer<String> c) {
        c.accept("123");
    }

    void two(Consumer<String> c) {
        c.andThen(t -> System.out.println(t)).accept("123");
    }
}