package com.chuyou.eshop.eshop.auth.service;

import com.chuyou.eshop.eshop.auth.domain.PriorityDTO;
import com.chuyou.eshop.eshop.auth.service.impl.Priority;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 9:22 下午
 * @Description: 权限管理模块的service组件接口
 */
public interface PriorityService {

    /**
     * 查询根权限
     * @return 根权限集合
     */
    List<PriorityDTO> listRootPriorities() throws IllegalAccessException, InstantiationException;

    /**
     * 根据父权限id查询子权限
     * @param parentId 父权限ID
     * @return 子权限
     */
    List<PriorityDTO> listChildPriorities(Long parentId) throws IllegalAccessException, InstantiationException;

    /**
     * 根据ID查询权限
     * @param id 权限ID
     * @return 权限
     */
    PriorityDTO getPriorityById(Long id) throws InstantiationException, IllegalAccessException;

    /**
     * 查询账号被授权的权限数
     * @param accountId 账号ID
     * @return 权限数
     */
    List<Priority> listAuthorizedByAccountId(Long accountId);

    /**
     * 判断账号是否存在对指定编号的权限的授权记录
     * @param accountId 账号ID
     * @param coce 权限编号
     * @return 是否存在授权记录
     */
    Boolean existAuthorizedByCode(Long accountId, String coce);

    /**
     * 新增权限
     * @param priorityDTO 权限
     */
    void savePriority(PriorityDTO priorityDTO) throws ParseException, InstantiationException, IllegalAccessException;

    /**
     * 更新权限
     * @param priorityDTO 权限
     */
    void updatePriority(PriorityDTO priorityDTO) throws ParseException, InstantiationException, IllegalAccessException;

    /**
     * 删除权限
     * @param id 权限ID
     * @return 处理结果
     */
    Boolean removePriority(Long id);
}
