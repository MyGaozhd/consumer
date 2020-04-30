package com.servi.cloud.consumer.concurrent.juc._17_juc_tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * 网络通信
 *
 * 比如说一种统计场景 统计信息不是很重要而且不需要很准确 而且数据量很大 每次都提交网络浪费链接资源
 * 20条 统计一起发送网络
 *
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20);

        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("20"));

        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "thread-" + i).start();

        }
    }
}
