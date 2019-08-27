package com.servi.cloud.consumer.gbus.test;

import com.servi.cloud.consumer.gbus.annotation.GBus;

public interface GBusInterface {

    @GBus(clazz = IGBusTest.class, method = "test", requestAdapter = GBusTestRequest.class, responseAdapter = GBusTestResponse.class)
    public String test(String a, String b);
}

