package com.servi.cloud.consumer.concurrent.juc._21_RefTypeAndThreadLocal;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
