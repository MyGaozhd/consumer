package com.servi.cloud.consumer.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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

    class post implements MessagePostProcessor {

        @Override
        public Message postProcessMessage(Message message) throws AmqpException {
            return null;
        }

        @Override
        public Message postProcessMessage(Message message, Correlation correlation) {
            return null;
        }
    }
}
