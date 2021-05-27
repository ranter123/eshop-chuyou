package com.chuyou.eshop.eshop.auth.service.impl;

import com.chuyou.eshop.eshop.auth.dao.PriorityDAO;
import com.chuyou.eshop.eshop.auth.domain.PriorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/2 8:27 下午
 * @Description: 移除权限操作
 */
@Component
@Scope("prototype")
public class RemovePriorityOperation implements PriorityOperation {

    /**
     * 权限DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 访问权限树节点
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
        removePriority(priority);
        return true;
    }

    /**
     * 删除权限
     * @param priority 权限树节点
     */
    private void removePriority(Priority priority) {
        priorityDAO.removePriority(priority.getId());
    }
}
