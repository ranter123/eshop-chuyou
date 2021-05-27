package com.chuyou.eshop.eshop.auth.dao;

import com.chuyou.eshop.eshop.auth.domain.RolePriorityRelationshipDO;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/2 6:02 上午
 * @Description: 角色和权限关系管理模块的DAO组件接口
 */
public interface RolePriorityRelationshipDAO {

    /**
     * 新增角色和权限的关联关系
     * @param rolePriorityRelationshipDO
     */
    void save(RolePriorityRelationshipDO rolePriorityRelationshipDO);

    /**
     * 根据权限ID查询记录数
     * @param priorityId 权限ID
     * @return 记录数
     */
    Long countByPriorityId(Long priorityId);

    /**
     * 根据ID查询角色和权限的关系
     * @param roleId 角色ID
     * @return 角色权限关系DO对象集合
     */
    List<RolePriorityRelationshipDO> listByRoleId(Long roleId);

    /**
     * 根据角色ID删除角色权限关联关系
     * @param roleId 角色ID
     */
    void removeByRoleId(Long roleId);
}
