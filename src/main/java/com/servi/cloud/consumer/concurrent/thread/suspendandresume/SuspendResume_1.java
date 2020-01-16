package com.servi.cloud.consumer.concurrent.thread.suspendandresume;

/**
 * 停止和恢复
 */
public class SuspendResume_1 {


    public static void main(String[] args) throws InterruptedException {
        PrintUtil util = new PrintUtil();
        MyThread thread = new MyThread("mythread", util);
        System.out.println("执行开始：" + thread.isAlive());
        thread.start();
        Thread.sleep(50);
        thread.suspend();
        util.print("打印");
        System.out.println("执行结束？：" + thread.isAlive());
    }

    static class MyThread extends Thread {

        PrintUtil util;

        MyThread(String name, PrintUtil util) {
            setName(name);
            this.util = util;
        }

        @Override
        public void run() {
            super.run();
            util.print("MyThread run方法");
            System.out.println("MyThread run方法：执行完毕");
        }
    }

    static class PrintUtil {
        public synchronized void print(String msg) {
            if (Thread.currentThread().getName().equals("mythread")) {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(msg);
            }
        }
    }
}
