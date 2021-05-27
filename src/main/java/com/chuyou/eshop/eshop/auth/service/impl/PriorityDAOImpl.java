package com.chuyou.eshop.eshop.auth.service.impl;

import com.chuyou.eshop.eshop.auth.dao.PriorityDAO;
import com.chuyou.eshop.eshop.auth.domain.PriorityDO;
import com.chuyou.eshop.eshop.auth.mapper.PriorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ranter
 * @Date: 2021/4/2 7:15 上午
 * @Description: 权限管理模块的DAO组件
 */
@Service
public class PriorityDAOImpl implements PriorityDAO {

    /**
     * 权限管理模块的mapper组件
     */
    @Autowired
    private PriorityMapper priorityMapper;

    /**
     * 查询根权限
     * @return 根权限集合
     */
    @Override
    public List<PriorityDO> listRootPriorities() throws IllegalAccessException, InstantiationException {
        return priorityMapper.listRootPriorities();
    }

    /**
     * 根据父权限ID查询子权限
     * @param parentId 父权限ID
     * @return 子权限
     */
    @Override
    public List<PriorityDO> listChildPriorities(Long parentId) {
        return priorityMapper.listChildPriorities(parentId);
    }

    /**
     * 根据ID查询权限
     * @param id 权限ID
     * @return 权限
     */
    @Override
    public PriorityDO getPriorityById(Long id) {
        return priorityMapper.getPriorityById(id);
    }

    /**
     * 查询账号被授权的菜单
     * @param parameters 查询参数
     * @return 账号被授权的菜单
     */
    @Override
    public List<PriorityDO> listAuthoriziedByAccountId(Map<String, Object> parameters) {
        return priorityMapper.listAuthroziedByAccountId(parameters);
    }

    /**
     * 根据权限ID查询账号ID
     * @param priorityId 权限ID
     * @return 账号ID集合
     */
    @Override
    public List<Long> listAccountIdsByPriorityId(Long priorityId) {
        return priorityMapper.listAccountIdsByPriorityId(priorityId);
    }

    /**
     * 统计账号对指定编号的权限是否有授权记录
     * @param accountId 账号ID
     * @param code 权限编号
     * @return 是否有授权记录
     */
    @Override
    public Long countAuthorizedByCode(Long accountId, String code) {
        return priorityMapper.countAuthorizedByCode(accountId, code);
    }

    /**
     * 统计账号对指定URL的权限是否有授权记录
     * @param accountId 账号ID
     * @param url 权限URL
     * @return 是否有授权记录
     */
    @Override
    public Long countAuthorizedByUrl(Long accountId, String url) {
        return priorityMapper.countAuthorizedByUrl(accountId, url);
    }

    /**
     * 新增权限
     * @param priorityDO 权限DO对象
     * @return 新增权限ID
     */
    @Override
    public Long savePriority(PriorityDO priorityDO) {
        priorityMapper.savePriority(priorityDO);
        return priorityDO.getId();
    }

    /**
     * 更新权限
     * @param priorityDO 权限DO对象
     */
    @Override
    public void updatePriority(PriorityDO priorityDO) {
        priorityMapper.updatePriority(priorityDO);
    }

    /**
     * 删除权限
     * @param id 权限ID
     */
    @Override
    public void removePriority(Long id) {
        priorityMapper.removePriority(id);
    }
}
