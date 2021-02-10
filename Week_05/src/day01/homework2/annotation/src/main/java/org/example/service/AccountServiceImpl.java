package org.example.service;

import org.example.annotation.Autowired;
import org.example.annotation.Service;
import org.example.dao.AccountDao;
import org.example.model.Account;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountDao accountDao;


    @Override
    public void transfer(String from, String to, double money) {
        Account fromAccount = accountDao.selectOne(from);
        Account toAccount = accountDao.selectOne(to);

        System.out.println(String.format("转账前，转出者的余额：%s，转入者的余额：%s",
                fromAccount.getMoney(),toAccount.getMoney()));
        fromAccount.setMoney(fromAccount.getMoney()-money);
        toAccount.setMoney(toAccount.getMoney()+money);
        //更新账户
        accountDao.updateAccount(fromAccount);
        accountDao.updateAccount(fromAccount);
        System.out.println(String.format("转账后，转出者的余额：%s，转入者的余额：%s",
                fromAccount.getMoney(),toAccount.getMoney()));
    }
}
