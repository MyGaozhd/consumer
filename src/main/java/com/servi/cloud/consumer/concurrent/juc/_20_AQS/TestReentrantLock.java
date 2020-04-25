package com.servi.cloud.consumer.concurrent.juc._20_AQS;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        TestReentrantLock t = new TestReentrantLock();
        new Thread(t::test).start();
    }

    public void test() {
        try {
            lock.lock();
            Thread.sleep(5000);
            System.out.println("111");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
