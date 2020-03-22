package com.servi.cloud.consumer.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAop {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        Math math = context.getBean(Math.class);
        math.div(1,2);
    }
}
