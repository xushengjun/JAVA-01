package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.AccountInfo;

public interface AccountService extends IService<AccountInfo> {

    void updateAccountBalance(String accountNo, Double amount);
}
