package com.servi.cloud.consumer.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "sty")
public class MessageReceiver {

    @RabbitHandler
    public void onRecive(String msg) {
        System.out.println("onRecive:" + msg);
    }
}
