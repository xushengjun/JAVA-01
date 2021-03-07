package com.gane.maple.service;

import com.gane.maple.entity.User;


public interface UserService {

    User selectByUserId(String userId);

    User selectByUserName(String userName);

    User selectByUserNameFromMaster(String userName);

    int insert(User user);
}
