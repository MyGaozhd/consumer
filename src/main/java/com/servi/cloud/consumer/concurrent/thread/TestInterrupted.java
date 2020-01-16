package com.servi.cloud.consumer.concurrent.thread;

/**
 * Interrupted
 */
public class TestInterrupted {
    public static void main(String[] args) throws InterruptedException {

        Thread.currentThread().interrupt();
        System.out.println("停止？：" + Thread.interrupted());
        System.out.println("停止？：" + Thread.interrupted());
    }
}
