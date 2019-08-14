package com.servi.cloud.consumer.annotation;

import java.lang.reflect.Proxy;

public class AnnotationProxy {

    public static <T> T find(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new AnnotationHandler(new AnnotationCheck()));
    }
}
