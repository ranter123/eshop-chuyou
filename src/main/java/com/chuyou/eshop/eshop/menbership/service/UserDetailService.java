package com.chuyou.eshop.eshop.menbership.service;

import com.chuyou.eshop.eshop.menbership.domain.UserDetailDTO;

/**
 * @Description: 用户详细信息管理service接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:41
 */
public interface UserDetailService {

    /**
     * 根据用户账号id查询用户详细信息
     * @param userAccountId 用户账号id
     * @return 用户详细信息
     * @throws Exception
     */
    UserDetailDTO getByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 更新用户详细信息
     * @param userDetail 用户详细信息
     * @throws Exception
     */
    void updateByUserAccountId(UserDetailDTO userDetail) throws Exception;


}
