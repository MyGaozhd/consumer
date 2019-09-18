package com.servi.cloud.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.servi.cloud.consumer.entry.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("findUserById/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public User findUserById(@PathVariable int id) {
        log.error("findUserById:" + id);
        return restTemplate.getForObject("http://provider/user/" + id, User.class);
    }

    /**
     * fallbackMethod
     *
     * @return
     */
    public User fallbackMethod(@PathVariable int id) {
        User user = new User();
        user.setName("error");
        return user;
    }
}
