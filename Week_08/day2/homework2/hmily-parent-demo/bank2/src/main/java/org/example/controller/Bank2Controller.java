package org.example.controller;

import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bank2Controller {
    @Autowired
    AccountService accountService;

    @RequestMapping("/transfer")
    public Boolean test2(@RequestParam("amount") Double amount) {
        this.accountService.updateAccountBalance("2", amount);
        return true;
    }

}

