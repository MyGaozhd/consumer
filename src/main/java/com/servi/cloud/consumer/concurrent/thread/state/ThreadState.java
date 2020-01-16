package com.servi.cloud.consumer.concurrent.thread.state;

/**
 * NEW,
 * RUNNABLE,
 * BLOCKED,
 * WAITING,
 * TIMED_WAITING,
 * TERMINATED;
 */
public class ThreadState {

    // new  -> runnable
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread("mythread");
        System.out.println(thread.getState());
        thread.start();
        thread.suspend();
        System.out.println(thread.getState());
        thread.resume();
        System.out.println(thread.getState());

    }

    static class MyThread extends Thread {
        int i = 0;

        MyThread(String name) {
            setName(name);
        }

        @Override
        public void run() {
            super.run();
            while (i <= 5000) {
                i++;
                System.out.println("执行开始：" + i);
            }
        }

        public int getI() {
            return i;
        }
    }
}
