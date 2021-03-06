package com.servi.cloud.consumer.concurrent.q;

/**
 * 缓存带来的可见性问题
 */
public class Q1_3 {
    boolean retry = true;

    private void retry() {
        System.out.println(" retry start ");
        while (retry) {
        }
        System.out.println(" retry end ");
    }

    public static void main(String[] args) throws InterruptedException {
        Q1_3 q = new Q1_3();
        // 创建线程，执行retry()操作
        Thread t1 = new Thread(q::retry);
        t1.setDaemon(true);
        // 启动线程
        t1.start();
        //等待 1 秒
        Thread.sleep(1000);
        //停止 retry
        q.retry = false;
        System.out.println(" set retry stop ");
    }
}







