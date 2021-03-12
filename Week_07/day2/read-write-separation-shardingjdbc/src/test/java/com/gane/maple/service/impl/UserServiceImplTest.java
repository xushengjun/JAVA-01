package com.gane.maple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gane.maple.entity.User;
import com.gane.maple.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void save(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserId(""+i);
            user.setUserName(""+i);
            users.add(user);
        }

        userService.saveBatch(users);
    }

//    @Test
//    public void selectOne(){
//        User user = userService.getById("id-0");
//        System.out.println(user);
//    }

    @Test
    public void selectAll(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> users = userService.list(queryWrapper);
        System.out.println(users.size());
    }

    @Test
    public void delete(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        boolean isRemove = userService.remove(queryWrapper);
        System.out.println(isRemove);
    }



}
