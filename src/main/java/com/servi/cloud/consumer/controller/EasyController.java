package com.servi.cloud.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class EasyController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){

        return "id="+System.currentTimeMillis();
    }
}
