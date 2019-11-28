package com.servi.cloud.consumer.concurrent.threadpool;

public class DBRunnable implements CrossesRunnable {

    @Override
    public void beforeRun() {

    }

    @Override
    public void afterRun() {

    }

    @Override
    public Object call() throws Exception {
        this.beforeRun();
        return null;
    }
}
