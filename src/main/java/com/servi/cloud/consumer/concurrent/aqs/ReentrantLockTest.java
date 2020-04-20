package com.servi.cloud.consumer.concurrent.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        ReentrantLockTest t = new ReentrantLockTest();
        new Thread(t::test).start();
    }

    public void test() {
        try {
            lock.lock();
            Thread.sleep(5000);
            System.out.println("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
