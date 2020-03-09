package com.servi.cloud.consumer.spring.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Configurable 通过类来定义容器中的bean
 */
public class TestConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        context.getBean("user1");
        System.out.println(context.getBean("user1").getClass().toString());
    }
}
