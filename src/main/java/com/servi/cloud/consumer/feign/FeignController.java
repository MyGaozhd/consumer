package com.servi.cloud.consumer.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private FeignInterface feignInterface;

    @GetMapping("/test")
    public String test() {
        return feignInterface.findUserById(100515).getName();
    }
}
