package com.servi.cloud.consumer.concurrent.reentrantlock;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock lock = new ReenterLock();
        Thread t1 = new Thread(lock);
//        Thread t2 = new Thread(lock);
        t1.start();
//        t2.start();
        t1.join();
//        t2.join();
        ServiLogger.log(i);

    }
}
