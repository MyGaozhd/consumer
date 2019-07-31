package com.servi.cloud.consumer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg) {
        System.out.println("send:" + msg);
        this.amqpTemplate.convertAndSend("sty", msg);
    }
}
