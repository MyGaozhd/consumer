package com.servi.cloud.consumer.annotation;

public interface IAnnotationCheck {

    @MethodCheck(check = true, value = "doAnnotationCheck")
    public void doAnnotationCheck(@ParamCheck(check = true, value = "KKKKKKKKKKK") String value);

    @MethodCheck(check = true, value = "doAnnotationCheck")
    public void unDoAnnotationCheck(@ParamCheck(check = false, value = "KKKKKKKKKKK") String value);
}
