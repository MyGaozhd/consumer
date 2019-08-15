package com.servi.cloud.consumer.service;

import com.servi.cloud.consumer.entry.User;

public interface IUserService {

    public User findUserById(String id);
}
