package com.servi.cloud.consumer.concurrent.q;

/**
 * 有序性问题
 */
public class Q2 {
    private static volatile boolean already = false;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; already == false; i++) {
            Q2.State state = new Q2.State();
            ThreadA threadA = new ThreadA(state);
            ThreadB threadB = new ThreadB(state);
            threadA.start(); threadB.start();
            threadA.join(); threadB.join();
        }
        System.out.println(i);
    }

    static class ThreadA extends Thread {
        private final Q2.State state;
        ThreadA(Q2.State state) {
            this.state = state;
        }
        public void run() {
            state.a = 1;
            state.b = 1;
            state.c = 1;
            state.d = 1;
        }
    }

    static class ThreadB extends Thread {
        private final Q2.State state;
        ThreadB(Q2.State state) {
            this.state = state;
        }
        public void run() {
            if (state.b == 1 && state.a == 0) {
                System.out.println("b=1");
                already = true;
            }
            if (state.c == 1 && (state.b == 0 || state.a == 0)) {
                System.out.println("c=1");
                already = true;
            }
            if (state.d == 1 && (state.a == 0 || state.b == 0 || state.c == 0)) {
                System.out.println("d==1");
                already = true;
            }
        }
    }

    static class State {
        public int a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
    }
}
