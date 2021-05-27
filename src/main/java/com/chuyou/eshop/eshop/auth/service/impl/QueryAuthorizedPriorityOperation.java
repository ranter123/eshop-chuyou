package com.chuyou.eshop.eshop.auth.service.impl;

import com.chuyou.eshop.eshop.auth.dao.PriorityDAO;
import com.chuyou.eshop.eshop.auth.domain.PriorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ranter
 * @Date: 2021/4/2 8:16 下午
 * @Description: 查询授权操作
 */
@Component
@Scope("prototype")
public class QueryAuthorizedPriorityOperation implements PriorityOperation {

    /**
     * 账号ID
     */
    private Long accountId;

    /**
     * 权限DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 执行权限数操作
     * @param priority 权限
     * @return 执行结果
     * @throws Exception
     */
    @Override
    public Boolean doExecute(Priority priority) throws Exception {
        List<Priority> targetChildren = new ArrayList<>();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("accountId", accountId);
        parameters.put("parentId", priority.getId());

        List<PriorityDO> children = priorityDAO.listAuthoriziedByAccountId(parameters);
        for (PriorityDO child : children) {
            Priority targetChild = child.clone(Priority.class);
            targetChild.execute(this);
            targetChildren.add(targetChild);
        }
        priority.setChildren(targetChildren);
        return true;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
