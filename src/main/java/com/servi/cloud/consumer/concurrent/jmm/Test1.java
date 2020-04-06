package com.servi.cloud.consumer.concurrent.jmm;

public class Test1 {

    private int count = 0;

    private void add() {
        int i = 0;
        while (i++ < 10000) {
            count += 1;
        }
    }

    /**
     * 运行结果小于20000
     * 我们假设线程 A 和线程 B 同时开始执行，
     * 那么第一次都会将 count=0 读到各自的 CPU 缓存里，
     * 执行完 count+=1 之后，各自 CPU 缓存里的值都是 1，同时写入内存后，我们会发现内存中是 1，而不是我们期望的 2。
     * 之后由于各自的 CPU 缓存里都有了 count 的值，两个线程都是基于 CPU 缓存里的 count 值来计算，所以导致最终 count 的值都是小于 20000 的。
     * 这就是缓存的可见性问题。
     */
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
