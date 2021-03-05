package com.zrs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrs.bean.Account;
import com.zrs.mapper.AccountMapper;
import com.zrs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description:  在类上面配置事务， 类中不需要事务的方法重新配置覆盖类的注解
* @Author:         Rongsheng Zhang
* @UpdateRemark:   修改内容
*/
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = -1,readOnly = false,rollbackFor ={Exception.class})
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    AccountMapper accountMapper;

    //查询不需要事务，传播配置为NOT_SUPPORTED
    @Transactional(propagation = Propagation.NOT_SUPPORTED,isolation = Isolation.DEFAULT,timeout = -1,readOnly = true,rollbackFor ={Exception.class})
    public Account getById(int id) {
        return null;
    }

    public int update(Account account) {
        return 0;
    }

    /**
     * 转账方法---这个业务方法需要在事务中运行！！！！
     */
    //@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = -1,readOnly = true,rollbackFor ={Exception.class})
    public int transferMoney(String fromName, String toName, double money) {

        //第一账户的钱减少啦！
        Account account1 = accountMapper.selectOne(new QueryWrapper<Account>().eq("username", fromName));
        account1.setMoney(account1.getMoney()-money);
        int count1 = accountMapper.updateById(account1);

        //模拟异常
        //int i=10000/0;

        //第二账户的钱增加啦！
        Account account2 = accountMapper.selectOne(new QueryWrapper<Account>().eq("username", toName));
        account2.setMoney(account2.getMoney()+money);
        int count2 = accountMapper.updateById(account2);

        return count1+count2;
    }
}
