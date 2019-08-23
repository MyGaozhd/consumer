package com.servi.cloud.consumer.controller;

import com.servi.cloud.consumer.zk.IZClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class EasyController {

    @Value("${clientParam}")
    String clientParam;
    @Autowired
    IZClient zClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        return "clientParam：" + clientParam + " id=" + System.currentTimeMillis();
    }

    @RequestMapping(value = "/zktest", method = RequestMethod.GET)
    public String zkTest() {

        //1、创建servi节点
        zClient.createEphemeral("/servi","servi");
        return  zClient.readData("servi");
    }
}
