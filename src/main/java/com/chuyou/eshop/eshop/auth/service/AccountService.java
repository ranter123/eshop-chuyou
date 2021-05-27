package com.chuyou.eshop.eshop.auth.service;

import com.chuyou.eshop.eshop.auth.domain.AccountDTO;
import com.chuyou.eshop.eshop.auth.domain.AccountQuery;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 7:30 下午
 * @Description: 账号管理service组件
 */
public interface AccountService {

    /**
     * 分页查询账号
     * @param query 查询条件
     * @return 账号
     */
    List<AccountDTO> listByPage(AccountQuery query) throws IllegalAccessException, InstantiationException;

    /**
     * 查询账号
     * @param id 账号ID
     * @return 账号
     */
    AccountDTO getById(Long id) throws InstantiationException, IllegalAccessException;

    /**
     * 新增账号
     * @param account 账号
     */
    void save(AccountDTO account) throws ParseException, InstantiationException, IllegalAccessException;

    /**
     * 更新账号
     * @param account
     */
    void update(AccountDTO account) throws InstantiationException, IllegalAccessException, ParseException;

    /**
     * 更新密码
     * @param account
     */
    void updatePassword(AccountDTO account) throws ParseException, InstantiationException, IllegalAccessException;

    /**
     * 删除账号
     * @param id
     */
    void remove(Long id);
}
