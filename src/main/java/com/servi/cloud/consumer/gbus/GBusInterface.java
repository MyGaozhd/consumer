package com.servi.cloud.consumer.gbus;

import java.lang.reflect.Method;

import com.servi.cloud.consumer.gbus.annotation.GBus;
import com.servi.cloud.consumer.gbus.test.IGBusTest;

public interface GBusInterface {

    @GBus(clazz = IGBusTest.class, method = "test")
    public String test(String a, String b);
}

