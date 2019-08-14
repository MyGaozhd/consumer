package com.servi.cloud.consumer.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnnotationHandler implements InvocationHandler {

    private final Object tatarget;

    public AnnotationHandler(Object tatarget) {
        this.tatarget = tatarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(tatarget, args);
        return result;
    }
}
