package com.servi.cloud.consumer.util.proxy.jdk;

import com.servi.cloud.consumer.util.log.ServiLogger;

public class RealHandlerInterface implements IHandlerInterface {
    @Override
    public void test() {
        ServiLogger.log("RealHandlerInterface: test");
    }
}
