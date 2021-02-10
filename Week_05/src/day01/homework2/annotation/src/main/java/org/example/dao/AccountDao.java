package org.example.dao;

import org.example.annotation.Repository;
import org.example.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository(value = "AccountDao")
public class AccountDao {

    private static Map<String,Account> accountMap = new HashMap<>(2);
    static {
        accountMap.put("jack",new Account("jack",20000));
        accountMap.put("rose",new Account("rose",30000));
    }


    public Account selectOne(String name){
        if (name == null || "".equals(name)){
            throw new RuntimeException("账户名不能为空");
        }
        Account account = accountMap.get(name);
        if (Objects.isNull(account)){
            throw new RuntimeException(String.format("该用户：%s尚未注册账户",name));
        }
        return account;
    }

    public void updateAccount(Account account){
        accountMap.put(account.getName(),account);
    }
}
