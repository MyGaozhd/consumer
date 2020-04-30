/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
package com.servi.cloud.consumer.concurrent.juc._12_Volatile;

import java.util.concurrent.TimeUnit;

public class T02_VolatileReference1 {

    boolean running = true;

    volatile static T02_VolatileReference1 T = new T02_VolatileReference1(true);

    public  T02_VolatileReference1 (boolean running) {
        this.running = running;
    }

    void m() {
        System.out.println("m start");
        while(running) {
			/*
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        new Thread(T::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T.running = false;

        // 测试 然后看现象
//        T01_Functional = new T02_VolatileReference1(false);
//        T01_Functional = null;
    }
}
