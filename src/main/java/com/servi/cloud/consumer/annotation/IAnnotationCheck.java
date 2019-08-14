package com.servi.cloud.consumer.annotation;

public interface IAnnotationCheck {

    @MethodCheck(check = true, value = "doAnnotationCheck")
    public void doAnnotationCheck();
}
