package com.servi.cloud.consumer.spring.config;

import com.servi.cloud.consumer.spring.User;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@Configurable
@ComponentScan("com.servi.cloud.consumer.spring")
public class DemoConfig {

    @Scope("singleton")
    @Bean(value = "user1")
    public User user() {
        return new User();
    }
}
