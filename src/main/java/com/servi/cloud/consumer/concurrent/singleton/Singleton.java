package com.servi.cloud.consumer.concurrent.singleton;

public class Singleton {
    private static Singleton instance = null;

    private Singleton() {
        System.out.println("初始化");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
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
                Singleton.getInstance();
            }).start();
        }
    }
}
