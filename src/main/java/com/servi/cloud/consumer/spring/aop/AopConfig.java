package com.servi.cloud.consumer.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean
    public Math math() {
        return new Math();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
