package com.servi.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class EasyController {

    @Value("${clientParam}")
    String clientParam;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        return "clientParam：" + clientParam + " id=" + System.currentTimeMillis();
    }
}
