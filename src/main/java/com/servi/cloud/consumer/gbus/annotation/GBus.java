package com.servi.cloud.consumer.gbus.annotation;

import com.servi.cloud.consumer.gbus.IRequestAdapter;
import com.servi.cloud.consumer.gbus.IResponseAdapter;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GBus {

    public Class<?> clazz();

    public String method();

    public Class<? extends IRequestAdapter> requestAdapter();

    public Class<? extends IResponseAdapter> responseAdapter();

}
