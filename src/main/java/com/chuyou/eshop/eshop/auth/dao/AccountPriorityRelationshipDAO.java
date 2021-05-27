package com.chuyou.eshop.eshop.auth.dao;

import com.chuyou.eshop.eshop.auth.domain.AccountPriorityRelationshipDO;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 9:38 下午
 * @Description: 账号和权限关系管理模块的DAO组件接口
 */
public interface AccountPriorityRelationshipDAO {

    /**
     * 根据权限ID查询记录数
     * @param priorityId 权限ID
     * @return 记录数
     */
    List countByPriorityId(Long priorityId);

    /**
     * 根据账号ID查询账号和权限的关联关系
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     */
    List<AccountPriorityRelationshipDO> listByAccountId(Long accountId);

    /**
     * 新增账号和权限的关联关系
     * @param accountPriorityRelationshipDO
     */
    void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);

    /**
     * 根据账号id删除账号和权限的关联关系
     * @param accountId 账号ID
     */
    void removeByAccountId(Long accountId);
}
