package com.servi.cloud.consumer.feign;

import com.servi.cloud.consumer.entry.User;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "provider")
public interface FeignInterface {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") int id);
}
