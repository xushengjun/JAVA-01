package com.gane.maple.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gane.maple.entity.User;

import java.util.List;


public interface UserService extends IService<User> {

    List<User> selectUsers();

}
