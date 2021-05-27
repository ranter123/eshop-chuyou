package com.chuyou.eshop.eshop.auth.dao.impl;

import com.chuyou.eshop.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.chuyou.eshop.eshop.auth.domain.AccountRoleRelationshipDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 10:21 下午
 * @Description:
 */
@Repository
public class AccountRoleRelationshipDAOImpl implements AccountRoleRelationshipDAO {

    @Override
    public Long countByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<AccountRoleRelationshipDO> listByAccountId(Long accountId) {
        return null;
    }

    @Override
    public List<Long> listAccountIdByRoleId(Long roleId) {
        return null;
    }

    @Override
    public void save(AccountRoleRelationshipDO relation) {

    }

    @Override
    public void removeByAccountId(Long accountId) {

    }
}
