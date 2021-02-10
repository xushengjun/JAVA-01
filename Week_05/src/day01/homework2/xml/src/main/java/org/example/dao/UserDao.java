package org.example.dao;

import org.example.model.User;

public class UserDao {

    public User selectOne(){
        return new User("小明",16);
    }
}
