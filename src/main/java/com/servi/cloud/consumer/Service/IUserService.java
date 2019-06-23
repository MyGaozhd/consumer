package com.servi.cloud.consumer.Service;

import com.servi.cloud.consumer.entry.User;

public interface IUserService {

    public User findUserById(String id);
}
