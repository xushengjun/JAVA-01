package org.example;

import org.example.feign.Bank2Client;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
@MapperScan("org.example.mapper")
public class Bank1ServerApplication {

    @Autowired
    private Bank2Client bank2Client;

    public static void main(String[] args) {
        SpringApplication.run(Bank1ServerApplication.class,args);
    }
    @GetMapping("/hi")
    public String hi(){
        System.out.println("我是bank1！");
        String result = bank2Client.hello();
        return result;
    }
}
