package com.chuyou.eshop.eshop.auth.dao;

import com.chuyou.eshop.eshop.auth.domain.AccountRoleRelationshipDO;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 9:37 下午
 * @Description: 账号角色关系管理模块DAO组件接口
 */
public interface AccountRoleRelationshipDAO {

    /**
     * 根据角色id来查询记录数
     * @param roleId 角色id
     * @return 记录数
     */
    Long countByRoleId(Long roleId);

    /**
     * 根据账号ID查询账号和角色关联关系
     * @param accountId 账号ID
     * @return 账号和角色关联关系
     */
    List<AccountRoleRelationshipDO> listByAccountId(Long accountId);

    /**
     * 根据角色ID查询账号ID集合
     * @param roleId 角色ID
     * @return 账号ID集合
     */
    List<Long> listAccountIdByRoleId(Long roleId);

    /**
     * 新增账号和角色关联关系
     * @param relation 账号和角色的关联关系
     */
    void save(AccountRoleRelationshipDO relation);

    /**
     * 根据账号ID删除账号和角色的关联关系
     * @param accountId 账号ID
     */
    void removeByAccountId(Long accountId);
}
