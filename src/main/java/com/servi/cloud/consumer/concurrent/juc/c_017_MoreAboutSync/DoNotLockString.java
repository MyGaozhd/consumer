/**
 * 不要以字符串常量作为锁定对象
 */
package com.servi.cloud.consumer.concurrent.juc.c_017_MoreAboutSync;

public class DoNotLockString {
	
	String s1 = "Hello";
	String s2 = "Hello";

	void m1() {
		synchronized(s1) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1");
        }
	}
	
	void m2() {
		synchronized(s2) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m2");
		}
	}

    void m3() {
        synchronized("Hello") {
            System.out.println("m3");
        }
    }

	public static void main(String[] args) {
        DoNotLockString t = new DoNotLockString();
		new Thread(t::m1).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m3).start();
	}
}
