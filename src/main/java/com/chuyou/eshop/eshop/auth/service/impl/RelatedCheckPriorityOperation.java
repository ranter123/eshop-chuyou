package com.chuyou.eshop.eshop.auth.service.impl;

import com.chuyou.eshop.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.chuyou.eshop.eshop.auth.dao.PriorityDAO;
import com.chuyou.eshop.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.chuyou.eshop.eshop.auth.domain.PriorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/2 7:53 下午
 * @Description: 检查权限是否被关联的操作
 */
@Component
@Scope("prototype")
public class RelatedCheckPriorityOperation implements PriorityOperation {

    /**
     * 关联检查结果
     */
    private Boolean relateCheckResult = false;

    /**
     * 权限管理模块的DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 角色和权限关系管理模块的DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    /**
     * 账号和权限关系管理模块的DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO accountRoleRelationshipDAO;

    /**
     * 权限执行操作
     * @param priority 权限
     * @return 执行结果
     * @throws Exception
     */
    @Override
    public Boolean doExecute(Priority priority) throws Exception {
        List<PriorityDO> priorityDOs = priorityDAO.listChildPriorities(priority.getId());

        if (priorityDOs != null && priorityDOs.size() > 0) {
            for (PriorityDO priorityDO : priorityDOs) {
                Priority priority1 = priorityDO.clone(Priority.class);
                priority1.execute(this);
            }
        }
        if (relateCheck(priority)) {
            this.relateCheckResult = true;
        }
        return false;
    }

    /**
     * 检查权限是否被任何一个角色或者是账号关联了
     * @param node 权限树节点
     * @return 是否被任何一个角色或者账号关联了
     */
    private Boolean relateCheck(Priority node) {
        Long roleRelatedCount = rolePriorityRelationshipDAO.countByPriorityId(node.getId());

        if (roleRelatedCount != null && roleRelatedCount > 0) {
            return true;
        }

        Long accountRelatedCount = accountRoleRelationshipDAO.countByRoleId(node.getId());
        if (accountRelatedCount != null && accountRelatedCount > 0) {
            return true;
        }

        return false;
    }

}
