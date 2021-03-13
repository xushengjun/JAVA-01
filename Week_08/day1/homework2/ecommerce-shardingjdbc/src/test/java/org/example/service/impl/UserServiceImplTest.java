package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.entity.User;
import org.example.service.UserService;
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
    public void saveUser() {
        User user = new User(null, "xsj", "123", "x",
                "43012345678976543", System.currentTimeMillis(), System.currentTimeMillis());
        boolean success = userService.save(user);
        System.out.println(success);
    }


    @Test
    public void deleteUser() {
        boolean success = userService.remove(new QueryWrapper<User>());
        System.out.println(success);
    }

    @Test
    public void updateUser(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",1370295535473528834L);

        User user = new User();
        user.setUserName("zzr");
        boolean success = userService.update(user,updateWrapper);
        System.out.println(success);
    }

    @Test
    public void saveBatchUser() {
        List<User> users = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            User user = new User(null, "xsj", "123", "x",
                    "43012345678976543", System.currentTimeMillis(), System.currentTimeMillis());
            users.add(user);
        }
        boolean success = userService.saveBatch(users);
        System.out.println(success);
    }




}
