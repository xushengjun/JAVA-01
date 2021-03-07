package com.gane.maple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ReadWriteSeparationMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadWriteSeparationMybatisApplication.class, args);
    }
}
