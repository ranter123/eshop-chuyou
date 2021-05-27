package com.chuyou.eshop.eshop.auth.dao;

import com.chuyou.eshop.eshop.auth.domain.RoleDO;
import com.chuyou.eshop.eshop.auth.domain.RoleQuery;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/2 5:50 上午
 * @Description: 角色管理DAO组件接口
 */
public interface RoleDAO {

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    List<RoleDO> listByPage(RoleQuery query);

    /**
     * 根据ID查询角色DO对象
     * @param id 角色id
     * @return 角色DO对象
     */
    RoleDO getById(Long id);

    /**
     * 新增角色
     * @param role 角色
     * @return 角色ID
     */
    Long save(RoleDO role);

    /**
     * 更新角色
     * @param role 角色DO对象
     */
    void update(RoleDO role);

    /**
     * 删除角色
     * @param id 角色ID
     */
    void remove(Long id);
}
