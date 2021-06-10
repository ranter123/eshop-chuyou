package com.chuyou.eshop.eshop.auth.dao.impl;

import com.chuyou.eshop.eshop.auth.dao.AccountDAO;
import com.chuyou.eshop.eshop.auth.domain.AccountDO;
import com.chuyou.eshop.eshop.auth.domain.AccountQuery;
import com.chuyou.eshop.eshop.auth.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 7:42 下午
 * @Description: 账号管理DAO组件
 */
@Repository
public class AccountDAOImpl implements AccountDAO {

    /**
     * 账号管理mapper组件
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    @Override
    public List<AccountDO> listByPage(AccountQuery query) {
        return accountMapper.listByPage(query);
    }

    /**
     * 根据ID查询账号
     * @param id 账号ID
     * @return 账号
     */
    @Override
    public AccountDO getById(Long id) {
        return accountMapper.getById(id);
    }

    /**
     * 新增账号
     * @param account 账号
     * @return 账号ID
     */
    @Override
    public Long save(AccountDO account) {
        accountMapper.save(account);
        return account.getId();
    }

    /**
     * 更新账号
     * @param account 账号
     */
    @Override
    public void update(AccountDO account) {
        accountMapper.update(account);
    }

    /**
     * 更新密码
     * @param account 账号
     */
    @Override
    public void updatePassword(AccountDO account) {
        accountMapper.updatePassword(account);
    }

    /**
     * 删除账号
     * @param id 账号ID
     */
    @Override
    public void remove(Long id) {
        accountMapper.remove(id);
    }
}
