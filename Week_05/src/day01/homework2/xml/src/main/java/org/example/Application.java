package org.example;

import org.example.factory.BeanFactory;
import org.example.service.UserService;

public class Application {

    public static UserService userService = (UserService) BeanFactory.getObject("userService");

    public static void main(String[] args) {
//        System.out.println(userService.getUser());
        System.out.println();
    }


}
