package org.example;

import org.example.entity.UserInfo;
import org.example.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }
    @GetMapping("/user")
    public List<UserInfo> listUser(){
        return userInfoMapper.findAll();
    }

}
