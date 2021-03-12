package com.gane.maple.service.impl;

import com.gane.maple.entity.User;
import com.gane.maple.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSelectList(){
        List<User> users = userService.selectUsers();
        System.out.println(users);
    }

    @Test
    public void select(){
        User user = userService.getById("1");
        System.out.println(user);
        Assert.notNull(user);
    }

}
