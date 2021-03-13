package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.example.entity.AccountInfo;
import org.example.mapper.AccountInfoMapper;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Override
    @Transactional
    @Hmily(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public void updateAccountBalance(String accountNo, Double amount) {
        String localTradeNo = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("******** Bank2 Service Begin try ..."+localTradeNo);

    }

    @Transactional
    public  void confirmMethod(String accountNo, Double amount) {
        String localTradeNo = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("******** Bank2 Service commit...  " +localTradeNo);
        if(accountInfoMapper.isExistConfirm(localTradeNo) > 0){ //幂等性校验，已经执行过了，什么也不用做
            log.info("******** Bank2 已经执行过confirm... 无需再次confirm "+localTradeNo );
            return ;
        }
        //正式增加金额
        accountInfoMapper.addAccountBalance(accountNo,amount);
        //添加confirm日志
        accountInfoMapper.addConfirm(localTradeNo);
    }

    @Transactional
    public  void cancelMethod(String accountNo, Double amount) {
        String localTradeNo = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("******** Bank2 Service begin cancel...  "+localTradeNo );

    }
}
