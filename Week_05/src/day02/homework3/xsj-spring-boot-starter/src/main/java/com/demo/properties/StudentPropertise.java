package com.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student")
@Data
public class StudentPropertise {
    private int id;
    private String name;

}
