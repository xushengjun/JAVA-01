package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser() {
        return new User("小明",16);
    }
}
