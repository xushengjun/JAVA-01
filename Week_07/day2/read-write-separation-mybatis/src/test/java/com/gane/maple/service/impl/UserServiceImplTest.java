package com.gane.maple.service.impl;

import com.gane.maple.entity.User;
import com.gane.maple.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSelect(){
        User user = userService.selectByUserName("maple_master");
        System.out.println(user);

        User user2 = userService.selectByUserName("maple_master");
        System.out.println(user2);
    }
    @Test
    public void testInsert(){
        int i = userService.insert(new User("2","test"));
        System.out.println("插入成功数量："+i);
    }
}
