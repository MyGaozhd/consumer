package com.servi.cloud.consumer.gbus.test;

import com.servi.cloud.consumer.gbus.annotation.GBus;

public interface GBusInterface {

    @GBus(clazz = IGBusTest.class)
    public String test(String a, String b);

    @GBus(clazz = IGBusTest.class, method = "test", requestAdapter = GBusTestRequest.class, responseAdapter = GBusTestResponse.class)
    public String test1(String a, String b);

    @GBus(clazz = IGBusTest.class, method = "test", autoParameterType = false, parameterTypes = {String.class, String.class})
    public String test2(String a, String b);
}

