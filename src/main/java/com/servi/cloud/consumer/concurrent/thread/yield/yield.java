package com.servi.cloud.consumer.concurrent.thread.yield;

/**
 * 放弃CPU资源
 */
public class yield {


    //内部停止，外部恢复
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread("mythread");
        thread.start();

        thread.join();
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
                Thread.yield();
                System.out.println("执行开始：" + i);
            }
        }

        public int getI() {
            return i;
        }
    }
}
