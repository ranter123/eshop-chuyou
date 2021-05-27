package com.chuyou.eshop.eshop.auth.service.impl;

import com.chuyou.eshop.eshop.auth.dao.AccountDAO;
import com.chuyou.eshop.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.chuyou.eshop.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.chuyou.eshop.eshop.auth.domain.*;
import com.chuyou.eshop.eshop.auth.service.AccountService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 7:35 下午
 * @Description: 账号管理service组件
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    /**
     * 账号管理DAO组件
     */
    @Autowired
    private AccountDAO accountDAO;

    /**
     * 账号和角色关系管理DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO roleRelationshipDAO;

    /**
     * 账号和权限关系管理DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO priorityRelationshipDAO;


    /**
     * 分页查询账号
     * @param query 查询条件
     * @return 账号
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Override
    public List<AccountDTO> listByPage(AccountQuery query) throws IllegalAccessException, InstantiationException {
        List<AccountDO> accounts = accountDAO.listByPage(query);
        List<AccountDTO> resultAccounts = ObjectUtils.convertList(accounts, AccountDTO.class);
        return resultAccounts;
    }

    /**
     * 根据ID查询账号
     * @param id 账号ID
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public AccountDTO getById(Long id) throws InstantiationException, IllegalAccessException {
        AccountDO account = accountDAO.getById(id);
        AccountDTO resultAccount = account.clone(AccountDTO.class);

        List<AccountRoleRelationshipDO> roleRelations =
                roleRelationshipDAO.listByAccountId(id);
        List<AccountRoleRelationshipDTO> resultRoleRelations =
                ObjectUtils.convertList(roleRelations, AccountRoleRelationshipDTO.class);
        resultAccount.setRoleRelations(resultRoleRelations);

        List<AccountPriorityRelationshipDO> priorityRelations =
                priorityRelationshipDAO.listByAccountId(id);
        List<AccountPriorityRelationshipDTO> resultPriorityRelations =
                ObjectUtils.convertList(priorityRelations, AccountPriorityRelationshipDTO.class);
        resultAccount.setPriorityRelations(resultPriorityRelations);

        return resultAccount;
    }

    /**
     * 新增账号
     * @param account 账号
     */
    @Override
    public void save(AccountDTO account) throws ParseException, InstantiationException, IllegalAccessException {
        account.setGmtCreate(DateUtils.getCurrentTime());
        account.setGmtModified(DateUtils.getCurrentTime());
        Long accountId = accountDAO.save(account.clone(AccountDO.class));
        saveRelations(account);
    }

    /**
     * 新增关联关系
     * @param account 账号
     */
    private void saveRelations(AccountDTO account) throws ParseException, InstantiationException, IllegalAccessException {
        for (AccountRoleRelationshipDTO roleRelation : account.getRoleRelations()) {
            roleRelation.setAccountId(account.getId());
            roleRelation.setGmtCreate(DateUtils.getCurrentTime());
            roleRelation.setGmtModified(DateUtils.getCurrentTime());
            roleRelationshipDAO.save(roleRelation.clone(AccountRoleRelationshipDO.class));
        }

        for (AccountPriorityRelationshipDTO priorityRelation : account.getPriorityRelations()) {
            priorityRelation.setAccountId(account.getId());
            priorityRelation.setGmtCreate(DateUtils.getCurrentTime());
            priorityRelation.setGmtModified(DateUtils.getCurrentTime());
            priorityRelationshipDAO.save(priorityRelation.clone(AccountPriorityRelationshipDO.class));
        }
    }

    /**
     * 更新账号
     * @param account 账号
     */
    @Override
    public void update(AccountDTO account) throws InstantiationException, IllegalAccessException, ParseException {
        account.setGmtModified(DateUtils.getCurrentTime());
        accountDAO.update(account.clone(AccountDO.class));

        roleRelationshipDAO.removeByAccountId(account.getId());
        priorityRelationshipDAO.removeByAccountId(account.getId());

        saveRelations(account);
    }

    /**
     * 更新密码
     * @param account 账号
     * @throws ParseException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public void updatePassword(AccountDTO account) throws ParseException, InstantiationException, IllegalAccessException {
        account.setGmtModified(DateUtils.getCurrentTime());
        accountDAO.updatePassword(account.clone(AccountDO.class));
    }

    /**
     * 删除账号
     * @param id 账号ID
     */
    @Override
    public void remove(Long id) {
        roleRelationshipDAO.removeByAccountId(id);
        priorityRelationshipDAO.removeByAccountId(id);
        accountDAO.remove(id);
    }
}
