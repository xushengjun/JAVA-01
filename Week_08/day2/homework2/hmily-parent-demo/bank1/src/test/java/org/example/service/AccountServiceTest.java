package org.example.service;

import org.example.entity.AccountInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void save(){
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountNo("123");
        accountInfo.setAccountName("zhangsan");
        accountInfo.setId(1L);
        accountInfo.setAccountBalance(100d);
        accountInfo.setAccountPassword("123");
        boolean successs = accountService.save(accountInfo);
        System.out.println(successs);
    }
}
