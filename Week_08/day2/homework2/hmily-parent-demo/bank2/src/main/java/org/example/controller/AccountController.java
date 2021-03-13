package org.example.controller;

import org.example.entity.AccountInfo;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank2")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/hi")
    public String hello(){
        return "hi,this is bank2!";
    }

    @GetMapping("/save")
    public boolean save(){
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountNo("123");
        accountInfo.setAccountName("zhangsan");
        accountInfo.setId(1L);
        accountInfo.setAccountBalance(100d);
        accountInfo.setAccountPassword("123");
        boolean success = accountService.save(accountInfo);
        System.out.println(success);
        return success;
    }
}
