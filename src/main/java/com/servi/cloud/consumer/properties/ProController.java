package com.servi.cloud.consumer.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController()
@RequestMapping("/pro")
public class ProController {

    @Autowired
    WebApplicationContext webApplicationConnect;

    @Autowired
    Environment env;

    @GetMapping("/protest")
    public String proTest() {
        return env.getProperty("spring.application.name");
    }
}
