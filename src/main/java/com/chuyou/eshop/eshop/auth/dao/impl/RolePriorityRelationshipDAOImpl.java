package com.chuyou.eshop.eshop.auth.dao.impl;

import com.chuyou.eshop.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.chuyou.eshop.eshop.auth.domain.RolePriorityRelationshipDO;
import com.chuyou.eshop.eshop.auth.mapper.RolePriorityRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/2 6:06 上午
 * @Description: 角色和权限关系管理模块的DAO组件
 */
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO {

    /**
     * 角色和权限关系管理模块的mapper组件
     */
    @Autowired
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;

    /**
     * 新增角色和权限的关联关系
     * @param rolePriorityRelationshipDO 角色和权限关联关系DO对象
     */
    @Override
    public void save(RolePriorityRelationshipDO rolePriorityRelationshipDO) {
        rolePriorityRelationshipMapper.save(rolePriorityRelationshipDO);
    }

    /**
     * 根据权限ID查询记录数
     * @param priorityId 权限ID
     * @return 记录数
     */
    @Override
    public Long countByPriorityId(Long priorityId) {
        return rolePriorityRelationshipMapper.countByPriorityId(priorityId);
    }

    /**
     * 根据角色ID查询角色和权限的关系
     * @param roleId 角色ID
     * @return 角色权限关系DO对象集合
     */
    @Override
    public List<RolePriorityRelationshipDO> listByRoleId(Long roleId) {
        return rolePriorityRelationshipMapper.listByRoleId(roleId);
    }

    /**
     * 根据角色ID删除角色权限关联关系
     * @param roleId 角色ID
     */
    @Override
    public void removeByRoleId(Long roleId) {
        rolePriorityRelationshipMapper.removeByRoleId(roleId);
    }
}
