package com.servi.cloud.consumer.concurrent.juc._17;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport unpark 和 park的先后
 * 注意，unpark函数可以先于park调用。
 * 比如线程B调用unpark函数，给线程A发了一个“许可”，
 * 那么当线程A调用park时，它发现已经有“许可”了，
 * 那么它会马上再继续运行，也就是不会阻塞。
 */
public class T14_TestLockSupport {
    public static void main(String[] args) {

        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("1");
    }
}
