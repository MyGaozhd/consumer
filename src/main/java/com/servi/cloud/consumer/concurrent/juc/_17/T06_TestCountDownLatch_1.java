package com.servi.cloud.consumer.concurrent.juc._17;

import java.util.concurrent.CountDownLatch;

/**
 * latch.countDown();
 * 可以被同一个线程同时调用多次
 */
public class T06_TestCountDownLatch_1 {
    public static void main(String[] args) {

        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) result += j;
                latch.countDown();
                System.out.println("count Down " + Thread.currentThread().getName());
                latch.countDown();
            }, i + "");
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

}
