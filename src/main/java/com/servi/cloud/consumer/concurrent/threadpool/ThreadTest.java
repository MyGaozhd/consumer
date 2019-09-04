package com.servi.cloud.consumer.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    public Thread getT() {
        t.start();
        Thread.currentThread();
        return t;
    }

    static ThreadPoolExecutor ee = new ThreadPoolExecutor(10, 100,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1)) {

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int k = i;
            ee.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aa:" + k);
                }
            });
        }

    }

}
