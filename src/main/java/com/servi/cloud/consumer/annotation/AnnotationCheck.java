package com.servi.cloud.consumer.annotation;

import com.servi.cloud.consumer.util.log.ServiLogger;

/**
 * 注解测试类
 */
@ClassCheck(check = true, value = "a")
public class AnnotationCheck extends BaseAnnotationCheck implements IAnnotationCheck {

    public static void main(String[] args) {

        AnnotationProxy.find(IAnnotationCheck.class).doAnnotationCheck("LLLLLLLLLLLLL");

        AnnotationProxy.find(IAnnotationCheck.class).unDoAnnotationCheck("LLLLLLLLLLLLL");
    }

    @FieldCheck(check = false, value = "fieldCheck1")
    private String fieldCheck1;

    @FieldCheck(check = true, value = "fieldCheck2")
    private String fieldCheck2;

    @MethodCheck(check = true, value = "doCheck")
    public boolean doCheck() {
        return false;
    }

    @MethodCheck(check = false, value = "unDoCheck")
    public boolean unDoCheck() {
        return false;
    }

    public void doParamCheck(@ParamCheck(check = true, value = "doParamCheck") String a) {
        ServiLogger.log("doParamCheck:" + a);
    }

    public void UnDoParamCheck(@ParamCheck(check = false, value = "UnDoParamCheck") String a) {
        ServiLogger.log("UnDoParamCheck:" + a);
    }

    public String getFieldCheck1() {
        return fieldCheck1;
    }

    public void setFieldCheck1(String fieldCheck1) {
        this.fieldCheck1 = fieldCheck1;
    }

    public String getFieldCheck2() {
        return fieldCheck2;
    }

    public void setFieldCheck2(String fieldCheck2) {
        this.fieldCheck2 = fieldCheck2;
    }

    @Override
    public void doAnnotationCheck(String value) {
        ServiLogger.log("doAnnotationCheck：" + value);
    }

    @Override
    public void unDoAnnotationCheck(String value) {
        ServiLogger.log("unDoAnnotationCheck：" + value);
    }
}
