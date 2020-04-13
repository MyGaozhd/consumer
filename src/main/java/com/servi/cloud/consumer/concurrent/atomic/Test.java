package com.servi.cloud.consumer.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        atomicInteger.compareAndSet(1,2);
    }
}
