package com.gane.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gane.maple.entity.User;
import com.gane.maple.mapper.UserMapper;
import com.gane.maple.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
