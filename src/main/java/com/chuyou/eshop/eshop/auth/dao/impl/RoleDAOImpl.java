package com.chuyou.eshop.eshop.auth.dao.impl;

import com.chuyou.eshop.eshop.auth.dao.RoleDAO;
import com.chuyou.eshop.eshop.auth.domain.RoleDO;
import com.chuyou.eshop.eshop.auth.domain.RoleQuery;
import com.chuyou.eshop.eshop.auth.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/2 5:53 上午
 * @Description: 角色管理模块DAO组件
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

    /**
     * 角色管理模块mapper组件
     */
    @Resource
    private RoleMapper roleMapper;

    /**
     * 角色管理模块DAO组件
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    @Override
    public List<RoleDO> listByPage(RoleQuery query) {
        return roleMapper.listByPage(query);
    }

    @Override
    public RoleDO getById(Long id) {
        return roleMapper.getById(id);
    }

    @Override
    public Long save(RoleDO role) {
        roleMapper.save(role);
        return role.getId();
    }

    @Override
    public void update(RoleDO role) {
        roleMapper.update(role);
    }

    @Override
    public void remove(Long id) {
        roleMapper.remove(id);
    }
}
