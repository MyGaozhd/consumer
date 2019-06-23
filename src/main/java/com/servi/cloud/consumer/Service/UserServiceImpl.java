package com.servi.cloud.consumer.Service;

import com.servi.cloud.consumer.dao.IUserDao;
import com.servi.cloud.consumer.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findUserById(String id) {
        User user = new User();
        user.setId(id);
        user.setName(id + "kk");
        user.setSex(id + "man");
        return user;
    }
}
