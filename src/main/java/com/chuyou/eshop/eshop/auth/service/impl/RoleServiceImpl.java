package com.chuyou.eshop.eshop.auth.service.impl;

import com.chuyou.eshop.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.chuyou.eshop.eshop.auth.dao.RoleDAO;
import com.chuyou.eshop.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.chuyou.eshop.eshop.auth.domain.*;
import com.chuyou.eshop.eshop.auth.service.RoleService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 9:36 下午
 * @Description: 角色service组件
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    /**
     * 角色管理模块DAO组件
     */
    @Autowired
    private RoleDAO roleDAO;

    /**
     * 账号角色关系管理模块DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    /**
     * 账号角色关系管理模块DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO accountRoleRelationshipDAO;

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    @Override
    public List<RoleDTO> listByPage(RoleQuery query) throws IllegalAccessException, InstantiationException {
        List<RoleDO> roles = roleDAO.listByPage(query);
        return ObjectUtils.convertList(roles, RoleDTO.class);
    }

    /**
     * 根据ID查询角色DO对象
     * @param id 角色ID
     * @return 角色DO对象
     */
    @Override
    public RoleDTO getById(Long id) throws InstantiationException, IllegalAccessException {
        RoleDO role = roleDAO.getById(id);
        if (role == null) {
            return null;
        }

        RoleDTO resultRole = role.clone(RoleDTO.class);

        List<RolePriorityRelationshipDO> relations =
                rolePriorityRelationshipDAO.listByRoleId(id);
        resultRole.setRolePriorityRelations(ObjectUtils.convertList(relations, RolePriorityRelationshipDTO.class));

        return resultRole;
    }

    /**
     * 新增角色
     * @param role 角色DTO对象
     */
    @Override
    public void save(RoleDTO role) throws ParseException, InstantiationException, IllegalAccessException {
        role.setGmtCreate(DateUtils.getCurrentTime());
        role.setGmtModified(DateUtils.getCurrentTime());
        Long roleId = roleDAO.save(role.clone(RoleDO.class));

        for (RolePriorityRelationshipDTO relation : role.getRolePriorityRelations()) {
            relation.setRoleId(roleId);
            relation.setGmtCreate(DateUtils.getCurrentTime());
            relation.setGmtModified(DateUtils.getCurrentTime());
            rolePriorityRelationshipDAO.save(relation.clone(RolePriorityRelationshipDO.class));
        }
    }

    /**
     * 更新角色
     * @param role 角色DTO对象
     */
    @Override
    public void update(RoleDTO role) throws ParseException, InstantiationException, IllegalAccessException {
        role.setGmtModified(DateUtils.getCurrentTime());
        roleDAO.update(role.clone(RoleDO.class));

        rolePriorityRelationshipDAO.removeByRoleId(role.getId());

        for(RolePriorityRelationshipDTO relation : role.getRolePriorityRelations()) {
            relation.setRoleId(role.getId());
            relation.setGmtModified(DateUtils.getCurrentTime());
            relation.setGmtCreate(DateUtils.getCurrentTime());
            rolePriorityRelationshipDAO.save(relation.clone(RolePriorityRelationshipDO.class));
        }

    }

    /**
     * 删除角色
     * @param id 角色ID
     * @return 处理结果
     */
    @Override
    public Boolean remove(Long id) {
        Long count = accountRoleRelationshipDAO.countByRoleId(id);
        if (count > 0L) {
            return false;
        }

        roleDAO.remove(id);
        rolePriorityRelationshipDAO.removeByRoleId(id);

        return true;
    }
}
