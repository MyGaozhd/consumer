package com.servi.cloud.consumer.concurrent.q;

/**
 * 缓存带来的可见性问题
 */
public class Q1 {

    private static long count = 0;

    private void add() {
        int i = 0;
        while (i++ < 10000) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Q1 q = new Q1();
        // 创建两个线程，执行add()操作
        Thread t1 = new Thread(q::add);
        Thread t2 = new Thread(q::add);

        // 启动两个线程
        t1.start();
        t2.start();
        // 等待两个线程执行结束
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
