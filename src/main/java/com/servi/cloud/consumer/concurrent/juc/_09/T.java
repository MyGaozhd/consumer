/**
 * synchronized获得的锁是可重入的
 */
package com.servi.cloud.consumer.concurrent.juc._09;

public class T {
	synchronized void m1() {
		System.out.println("m1 start");
		m2();
		System.out.println("m1 end");
	}
	
	synchronized void m2() {
		System.out.println("m2");
	}

	public static void main(String[] args) {
		new T().m1();
	}
}
