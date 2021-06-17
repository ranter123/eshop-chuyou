package com.chuyou.eshop.eshop.menbership.dao;

import com.chuyou.eshop.eshop.menbership.domain.UserDetailDO;

/**
 * @Description: 用户详细信息管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:40
 */
public interface UserDetailDAO {
    /**
     * 新增用户详细信息
     * @param userDetail 用户详细信息
     */
    void save(UserDetailDO userDetail);

    /**
     * 根据用户账号id查询用户详细信息
     * @param userAccountId 用户账号id
     * @return 用户详细信息
     */
    UserDetailDO getByUserAccountId(Long userAccountId);

    /**
     * 更新用户详细信息
     * @param userDetail 用户详细信息
     */
    void updateByUserAccountId(UserDetailDO userDetail);
}
