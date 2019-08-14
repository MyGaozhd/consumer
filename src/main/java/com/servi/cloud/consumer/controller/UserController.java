package com.servi.cloud.consumer.controller;

import com.servi.cloud.consumer.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("findUserById/{id}")
    public User findUserById(@PathVariable int id) {

        return restTemplate.getForObject("http://provider/user/" + id, User.class);
    }
}
