package com.gane.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gane.maple.entity.User;
import com.gane.maple.mapper.UserMapper;
import com.gane.maple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> selectUsers(){
        String sql = "select * from user";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }

}
