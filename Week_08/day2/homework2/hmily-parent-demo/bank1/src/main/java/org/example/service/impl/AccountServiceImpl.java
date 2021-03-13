package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.example.entity.AccountInfo;
import org.example.feign.Bank2Client;
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

    @Autowired
    private Bank2Client bank2Client;

    @Override
    @Transactional
    @Hmily(confirmMethod = "commit", cancelMethod = "rollback")
    public  void updateAccountBalance(String accountNo, Double amount) {
        //事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("******** Bank1 Service  begin try...  "+transId );
        int existTry = accountInfoMapper.isExistTry(transId);
        //try幂等校验
        if(existTry>0){
            log.info("******** Bank1 Service 已经执行try，无需重复执行，事务id:{}  "+transId );
            return ;
        }
        //try悬挂处理
        if(accountInfoMapper.isExistCancel(transId)>0 || accountInfoMapper.isExistConfirm(transId)>0){
            log.info("******** Bank1 Service 已经执行confirm或cancel，悬挂处理，事务id:{}  "+transId );
            return ;
        }
        //从账户扣减
        if(accountInfoMapper.subtractAccountBalance(accountNo ,amount )<=0){
            //扣减失败
            throw new HmilyRuntimeException("bank1 exception，扣减失败，事务id:{}"+transId);
        }
        //增加本地事务try成功记录，用于幂等性控制标识
        accountInfoMapper.addTry(transId);

        //远程调用bank2
        if(!bank2Client.transfer(amount)){
            throw new HmilyRuntimeException("bank2Client exception，事务id:{}"+transId);
        }
        if(amount==10){//异常一定要抛在Hmily里面
            throw new RuntimeException("bank1 make exception  10");
        }
        log.info("******** Bank1 Service  end try...  "+transId );
    }


    @Transactional
    public  void commit( String accountNo, double amount) {
        String localTradeNo = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("******** Bank1 Service begin commit..."+localTradeNo );
    }
    @Transactional
    public void rollback( String accountNo, double amount) {
        String localTradeNo = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("******** Bank1 Service begin rollback...  " +localTradeNo);
        if(accountInfoMapper.isExistTry(localTradeNo) == 0){ //空回滚处理，try阶段没有执行什么也不用做
            log.info("******** Bank1 try阶段失败... 无需rollback "+localTradeNo );
            return;
        }
        if(accountInfoMapper.isExistCancel(localTradeNo) > 0){ //幂等性校验，已经执行过了，什么也不用做
            log.info("******** Bank1 已经执行过rollback... 无需再次rollback " +localTradeNo);
            return;
        }
        //再将金额加回账户
        accountInfoMapper.addAccountBalance(accountNo,amount);
        //添加cancel日志，用于幂等性控制标识
        accountInfoMapper.addCancel(localTradeNo);
        log.info("******** Bank1 Service end rollback...  " +localTradeNo);
    }

}
