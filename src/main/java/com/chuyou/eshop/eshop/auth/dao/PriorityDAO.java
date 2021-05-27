package com.chuyou.eshop.eshop.auth.dao;

import java.util.List;
import java.util.Map;
import com.chuyou.eshop.eshop.auth.domain.PriorityDO;

/**
 * @Author: ranter
 * @Date: 2021/4/1 10:51 下午
 * @Description: 权限管理模块的DAO组件接口
 */
public interface PriorityDAO {

    /**
     * 查询根权限
     * @return 根权限集合
     */
    List<PriorityDO> listRootPriorities() throws IllegalAccessException, InstantiationException;

    /**
     * 根据父权限ID查询子权限
     * @param parentId
     * @return
     */
    List<PriorityDO> listChildPriorities(Long parentId);

    /**
     * 根据ID查询权限
     * @return 权限
     */
    PriorityDO getPriorityById(Long id);

    /**
     * 查询账号被授权的菜单
     * @param parameters 查询参数
     * @return 菜单
     */
    List<PriorityDO> listAuthoriziedByAccountId(Map<String, Object> parameters);

    /**
     * 根据权限ID查询账号ID
     * @param priorityId 权限ID
     * @return 账号ID
     */
    List<Long> listAccountIdsByPriorityId(Long priorityId);

    /**
     * 统计账号对指定编号的权限是否有授权记录
     * @param accountId 账号ID
     * @param code 权限编号
     * @return 是否有授权记录
     */
    Long countAuthorizedByCode(Long accountId, String code);

    /**
     * 统计账号对指定URL的权限是否有授权记录
     * @param accountId 账号ID
     * @param url 权限URL
     * @return 是否有授权记录
     */
    Long countAuthorizedByUrl(Long accountId, String url);

    /**
     * 新增权限
     * @param priorityDO 权限DO对象
     * @return
     */
    Long savePriority(PriorityDO priorityDO);

    /**
     * 更新权限
     * @param priorityDO 权限DO对象
     */
    void updatePriority(PriorityDO priorityDO);

    /**
     * 删除权限
     * @param id 权限ID
     */
    void removePriority(Long id);

}
