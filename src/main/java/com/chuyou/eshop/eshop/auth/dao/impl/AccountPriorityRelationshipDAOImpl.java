package com.chuyou.eshop.eshop.auth.dao.impl;

import com.chuyou.eshop.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.chuyou.eshop.eshop.auth.domain.AccountPriorityRelationshipDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 10:14 下午
 * @Description:
 */
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {



    /**
     * 根据权限id查询记录数
     * @param priorityId 权限ID
     * @return 记录数
     */
    @Override
    public List countByPriorityId(Long priorityId) {
        return null;
    }

    @Override
    public List<AccountPriorityRelationshipDO> listByAccountId(Long accountId) {
        return null;
    }

    @Override
    public void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO) {

    }

    @Override
    public void removeByAccountId(Long accountId) {

    }
}
