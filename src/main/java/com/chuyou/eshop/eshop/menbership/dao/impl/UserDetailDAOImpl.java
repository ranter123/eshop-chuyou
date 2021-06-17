package com.chuyou.eshop.eshop.menbership.dao.impl;

import com.chuyou.eshop.eshop.menbership.dao.UserDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.UserDetailDO;
import com.chuyou.eshop.eshop.menbership.mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户详细信息管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:43
 */
@Repository
public class UserDetailDAOImpl implements UserDetailDAO {

    /**
     * 用户详细信息管理mapper组件
     */
    @Autowired
    private UserDetailMapper userDetailMapper;

    /**
     * 新增用户详细信息
     * @param userDetail 用户详细信息
     */
    @Override
    public void save(UserDetailDO userDetail) {
        userDetailMapper.save(userDetail);
    }

    /**
     * 根据用户账号id查询用户详细信息
     * @param userAccountId 用户账号id
     * @return 用户详细信息
     */
    @Override
    public UserDetailDO getByUserAccountId(Long userAccountId) {
        return userDetailMapper.getByUserAccountId(userAccountId);
    }

    /**
     * 更新用户详细信息
     * @param userDetail 用户详细信息
     */
    @Override
    public void updateByUserAccountId(UserDetailDO userDetail) {
        userDetailMapper.updateByUserAccountId(userDetail);
    }
}
