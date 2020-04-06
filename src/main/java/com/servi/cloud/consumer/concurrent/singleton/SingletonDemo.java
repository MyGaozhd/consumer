package com.servi.cloud.consumer.concurrent.singleton;

public class SingletonDemo {
    private static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println("初始化");
    }

    @Override
    public String toString() {
        return this.hashCode() + "";
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    /**
     * 运行结果
     * <p>
     * 初始化
     * 初始化
     * 初始化
     * 初始化
     * 初始化
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(SingletonDemo.getInstance().toString());
            }).start();
        }
    }
}
