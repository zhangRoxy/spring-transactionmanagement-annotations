package com.zrs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zrs.bean.Account;

public interface AccountService extends IService<Account> {
    Account getById(int id);
    int update(Account account);
    /**
     * 业务逻辑方法--转账
     */
    int transferMoney(String fromName,String toName,double money);

}
