package com.chuyou.eshop.eshop.auth.service;

import com.chuyou.eshop.eshop.auth.domain.RoleDTO;
import com.chuyou.eshop.eshop.auth.domain.RoleQuery;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 9:17 下午
 * @Description: 角色管理模块service组件接口
 */
public interface RoleService {

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    List<RoleDTO> listByPage(RoleQuery query) throws IllegalAccessException, InstantiationException;

    /**
     * 根据ID查询角色DTO对象
     * @param id 角色ID
     * @return 角色DTO对象
     */
    RoleDTO getById(Long id) throws InstantiationException, IllegalAccessException;

    /**
     * 新增角色
     * @param role 角色DTO对象
     */
    void save(RoleDTO role) throws ParseException, InstantiationException, IllegalAccessException;

    /**
     * 更新角色
     * @param role 角色DTO对象
     */
    void update(RoleDTO role) throws ParseException, InstantiationException, IllegalAccessException;

    /**
     * 删除角色
     * @param id 角色ID
     * @return 处理结果
     */
    Boolean remove(Long id);
}
