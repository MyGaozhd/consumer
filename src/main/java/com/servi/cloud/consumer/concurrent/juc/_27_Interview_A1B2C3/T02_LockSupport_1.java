package com.servi.cloud.consumer.concurrent.juc._27_Interview_A1B2C3;


import java.util.concurrent.locks.LockSupport;

//Locksupport park 当前线程阻塞（停止）
//unpark(Thread t)

public class T02_LockSupport_1 {

    static Thread t1 = null, t2 = null;
    public static void main(String[] args) throws Exception {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < aC.length; i++) {
                    System.out.print(aC[i]);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                LockSupport.unpark(t2);
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                for (int i = 0; i < aI.length ; i++) {
                    System.out.print(aI[i]);
                    LockSupport.unpark(t1);
                    LockSupport.park();
                }
            }
        });
        t2.start();
        Thread.sleep(1000);
        t1.start();
    }
}


