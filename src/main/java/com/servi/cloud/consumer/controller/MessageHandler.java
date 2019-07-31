package com.servi.cloud.consumer.controller;

import com.servi.cloud.consumer.config.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageHandler {

    @Autowired
    private MessageSender sender;

    @GetMapping("send/{msg}")
    public String send(@PathVariable String msg) {

        sender.send(msg);
        return "ok";
    }
}
