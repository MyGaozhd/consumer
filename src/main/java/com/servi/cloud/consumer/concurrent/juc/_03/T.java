/**
 * synchronized关键字
 * 对某个对象加锁
 */
package com.servi.cloud.consumer.concurrent.juc._03;

public class T {

    private int count = 10;

    public synchronized void m() { //等同于在方法的代码执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }


}
