package com.servi.cloud.consumer.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.servi.cloud.consumer.util.log.ServiLogger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.netflix.hystrix.HystrixCommand.*;
import static com.netflix.hystrix.HystrixCommand.Setter.*;

public class TestHystrixCommand extends HystrixCommand<String> {

    private String name;

    protected TestHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(name));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        ServiLogger.log(" run ");
        return this.name + " >>>>>> " + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "失败了~~~~";
    }

    @Override
    protected String getCacheKey() {
        return this.name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        String result = new TestHystrixCommand("TestHystrixCommand").execute();
        ServiLogger.log(result);

        Future<String> future = new TestHystrixCommand("TestHystrixCommand").queue();
        ServiLogger.log(future.get());

        context.shutdown();
    }
}
