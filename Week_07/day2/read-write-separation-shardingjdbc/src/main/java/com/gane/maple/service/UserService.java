package com.gane.maple.service;

import com.gane.maple.entity.User;

import java.util.List;


public interface UserService {

    List<User> selectUsers();

    User selectByUserId(String userId);

    User selectByUserName(String userName);

    User selectByUserNameFromMaster(String userName);

    int insert(User user);
}
