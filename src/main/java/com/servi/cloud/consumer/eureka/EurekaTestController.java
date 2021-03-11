package com.servi.cloud.consumer.eureka;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/eurekat")
public class EurekaTestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/getclient")
    @HystrixCommand
    public List<ServiceInstance> getDiscoveryClient() {
        return discoveryClient.getInstances("consumer");
    }
}
