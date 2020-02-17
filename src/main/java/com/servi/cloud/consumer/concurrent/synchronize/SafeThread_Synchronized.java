package com.servi.cloud.consumer.concurrent.synchronize;

/**
 * 实例变量线程安全 demo
 */
public class SafeThread_Synchronized {

    public static void main(String[] args) {
        //单个实例对象需要 synchronized
//        test1();

        //多个实例对象不需要 synchronized
        test2();
    }

    /**
     * 单个实例对象需要 synchronized
     */
    public static void test1() {
        NameHolder holder = new NameHolder();
        Thread threada = new TestThread(holder, "a");
        Thread threadb = new TestThread(holder, "b");
        threada.start();
        threadb.start();
    }

    /**
     * 多个实例对象不需要 synchronized
     */
    public static void test2() {
        NameHolder holdera = new NameHolder();
        NameHolder holderb = new NameHolder();
        Thread threada = new TestThread(holdera, "a");
        Thread threadb = new TestThread(holderb, "b");
        threada.start();
        threadb.start();
    }

    static class NameHolder {
        private String name = "";

        public synchronized void setName(String name) {
            this.name = name;
            if (name.equals("a")) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("name is a :" + this.name);
            } else {
                System.out.println("name is b :" + this.name);
            }
        }
    }

    static class TestThread extends Thread {
        private NameHolder holder;
        private String name;

        public TestThread(NameHolder holder, String name) {
            this.holder = holder;
            this.name = name;
        }

        @Override
        public void run() {
            super.run();
            holder.setName(this.name);
        }
    }
}
