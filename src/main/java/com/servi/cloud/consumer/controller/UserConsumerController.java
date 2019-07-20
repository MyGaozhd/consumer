package com.servi.cloud.consumer.controller;

import java.util.concurrent.Executors;

import com.servi.cloud.consumer.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;

@RestController
public class UserConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("userConsumer/{id}")
    public String UserConsumer(@PathVariable int id) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = id; i < id + 10000; i++) {
            service.execute(new AddTask(i * 50));
        }
        return "success";
    }

    class AddTask implements Runnable {

        private int id;

        public AddTask(int id) {
            this.id = id;
        }

        private RestTemplate restTemplate = new RestTemplate();

        @Override
        public void run() {

            restTemplate.getForObject("http://localhost:8080/addusers/" + id, String.class);
            System.out.println(id + "~" + id + 50);
        }
    }
}
