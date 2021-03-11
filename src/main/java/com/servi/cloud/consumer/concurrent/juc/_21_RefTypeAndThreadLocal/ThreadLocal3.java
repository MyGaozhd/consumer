/**
 * ThreadLocal线程局部变量
 */
package com.servi.cloud.consumer.concurrent.juc._21_RefTypeAndThreadLocal;

public class ThreadLocal3 {

    public static void main(String[] args) {
        ThreadLocal<M> t1 = new ThreadLocal<>();
        t1.set(new M());
        t1.get();
        t1.remove();

        ThreadLocal<M> t2 = new ThreadLocal<>();
        t2.set(new M());
        t2.get();
        t2.remove();
    }
}