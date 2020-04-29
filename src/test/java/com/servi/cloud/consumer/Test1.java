package com.servi.cloud.consumer;

import org.openjdk.jmh.annotations.*;
import test.HotCode;

public class Test1 {

    @Benchmark
    @Warmup(iterations = 1,time = 3)//预热 迭代 iterations 1次 然后等待 time 3秒钟
    @Fork(5)//启动多少个线程进行执行
    @BenchmarkMode(Mode.Throughput) //吞吐量
    @Measurement(iterations = 1,time = 3)
    public void test(){
        HotCode.main(null);
    }
}
