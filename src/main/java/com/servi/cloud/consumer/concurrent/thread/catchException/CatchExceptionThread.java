package com.servi.cloud.consumer.concurrent.thread.catchException;

public class CatchExceptionThread extends Thread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "：000");
        CatchExceptionThread thread = new CatchExceptionThread(){
        };
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(Thread.currentThread().getName() + "：111");
            }
        });
        thread.setName("CatchExceptionThread");
        thread.start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
