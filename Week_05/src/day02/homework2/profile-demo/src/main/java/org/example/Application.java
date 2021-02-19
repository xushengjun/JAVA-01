package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class,args);
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        for (String profile : profiles) {
            System.out.println(profile);
        }
    }
}
