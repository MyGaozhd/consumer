package com.servi.cloud.consumer.gbus.test;

import java.lang.reflect.Method;

import com.servi.cloud.consumer.gbus.annotation.GBus;
import com.servi.cloud.consumer.gbus.test.IGBusTest;

public interface GBusInterface {

    @GBus(clazz = IGBusTest.class, method = "test", requestAdapter = GBusTestRequest.class, responseAdapter = GBusTestResponse.class)
    public String test(String a, String b);
}

