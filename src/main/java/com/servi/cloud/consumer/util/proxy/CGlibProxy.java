package com.servi.cloud.consumer.util.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGlibProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> cls) {

        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        doTest();
        Object result = methodProxy.invokeSuper(o, objects);
        return null;
    }

    private void doTest() {
        System.out.println("lllllllll");
    }
}
