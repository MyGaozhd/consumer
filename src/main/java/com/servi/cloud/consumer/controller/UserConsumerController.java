package com.servi.cloud.consumer.controller;

import com.servi.cloud.consumer.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("userConsumer/{id}")
    public User UserConsumer(@PathVariable String id) {

        return restTemplate.getForObject("http://localhost:8080/user/"+id,User.class);
    }
}
