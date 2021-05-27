package com.chuyou.eshop.eshop.auth.dao;

import com.chuyou.eshop.eshop.auth.domain.AccountDO;
import com.chuyou.eshop.eshop.auth.domain.AccountQuery;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 7:38 下午
 * @Description: 账号管理DAO组件
 */
public interface AccountDAO {

    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    List<AccountDO> listByPage(AccountQuery query);

    /**
     * 根据ID查询账号
     * @param id 账号ID
     * @return 账号
     */
    AccountDO getById(Long id);

    /**
     * 新增账号
     * @param account 账号
     * @return 账号ID
     */
    Long save(AccountDO account);

    /**
     * 更新账号
     * @param account 账号
     */
    void update(AccountDO account);

    /**
     * 更新密码
     * @param account 账号
     */
    void updatePassword(AccountDO account);

    /**
     * 删除账号
     * @param id 账号ID
     */
    void remove(Long id);
}
