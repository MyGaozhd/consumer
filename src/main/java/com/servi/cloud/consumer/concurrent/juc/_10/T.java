/**
 * synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 */
package com.servi.cloud.consumer.concurrent.juc._10;

public class T {
    synchronized void m() {
        System.out.println("m start");

        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }

}

class TT extends T {
    @Override
    synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}
