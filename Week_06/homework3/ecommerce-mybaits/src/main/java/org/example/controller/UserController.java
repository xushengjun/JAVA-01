package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user",produces = "application/json; charset=utf-8")//解决中文乱码
public class UserController {

}
