package com.chuyou.eshop.eshop.menbership.service.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.menbership.dao.UserDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.UserDetailDO;
import com.chuyou.eshop.eshop.menbership.domain.UserDetailDTO;
import com.chuyou.eshop.eshop.menbership.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 用户详细信息管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailServiceImpl implements UserDetailService {

    /**
     * 用户详细信息管理DAO组件
     */
    @Autowired
    private UserDetailDAO userDetailDAO;

    /**
     * 根据用户账号id查询用户详细信息
     * @param userAccountId 用户账号id
     * @return 用户详细信息
     */
    @Override
    public UserDetailDTO getByUserAccountId(Long userAccountId) throws Exception {
        return userDetailDAO.getByUserAccountId(userAccountId).clone(UserDetailDTO.class);
    }

    /**
     * 更新用户详细信息
     * @param userDetail 用户详细信息
     */
    @Override
    public void updateByUserAccountId(UserDetailDTO userDetail) throws Exception {
        userDetail.setGmtModified(DateUtils.getCurrentTime());
        userDetailDAO.updateByUserAccountId(userDetail.clone(UserDetailDO.class));
    }


}
