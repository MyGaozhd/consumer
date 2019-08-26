package com.servi.cloud.consumer.gbus;

import java.lang.reflect.Proxy;

/**
 * 实现服务接口路由功能
 */
public class GBusRouter {

    public static <T> T operate(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new GBusHandler());
    }
}
