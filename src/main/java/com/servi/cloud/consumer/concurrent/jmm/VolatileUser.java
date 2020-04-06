package com.servi.cloud.consumer.concurrent.jmm;

public class VolatileUser {
    volatile int age = 0;

    public void add() {
        age++;
    }
}
