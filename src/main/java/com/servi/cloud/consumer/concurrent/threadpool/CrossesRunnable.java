package com.servi.cloud.consumer.concurrent.threadpool;

import java.util.concurrent.Callable;

public interface CrossesRunnable extends Callable {
    public void beforeRun();

    public void afterRun();
}
