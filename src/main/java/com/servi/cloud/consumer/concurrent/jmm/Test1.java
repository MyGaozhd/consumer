package com.servi.cloud.consumer.concurrent.jmm;

public class Test1 {

    private int count = 0;

    private void add() {
        int i = 0;
        while (i++ < 10000) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Test1 test = new Test1();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(() -> {
            test.add();
        });
        Thread th2 = new Thread(() -> {
            test.add();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        System.out.println(test.count);
    }
}
