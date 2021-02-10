package org.example.service;

public interface AccountService {
    /**
     * 转账
     * @param from 转账人
     * @param to 收款人
     * @param money 金额
     */
    void transfer(String from,String to,double money);
}
