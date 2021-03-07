package com.gane.maple.dao;

import com.gane.maple.entity.User;

public interface UserDAO {

    User selectByUserId(String userId);

    User selectByUserName(String userName);

    User selectByUserIdWithoutDataSourceRouting(String userId);

    User selectByUserNameWithoutDataSourceRouting(String userName);

    int inser(User user);
}
