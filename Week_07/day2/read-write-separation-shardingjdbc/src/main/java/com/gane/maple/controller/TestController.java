package com.gane.maple.controller;

import com.gane.maple.entity.User;
import com.gane.maple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maple
 * @date 2021/3/2
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryUser")
    public User queryUser() {

        User userFromMaster = userService.selectByUserId("1");

        User userFromSlave = userService.selectByUserName("maple_slave");

        return userFromSlave;
    }
}
