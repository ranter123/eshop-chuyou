package com.chuyou.eshop.eshop.menbership.dao;

import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDO;

/**
 * @Description: 会员等级管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 17:17
 */
public interface MemberLevelDAO {

    /**
     * 根据用户账号id查询会员等级
     * @param userAccountId 用户账号id
     * @return 会员等级
     * @throws Exception
     */
    MemberLevelDO getByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 新增会员等级
     * @param memberLevel 会员等级
     * @throws Exception
     */
    void save(MemberLevelDO memberLevel) throws Exception;

    /**
     * 更新会员等级
     * @param memberLevel 会员等级
     * @throws Exception
     */
    void update(MemberLevelDO memberLevel) throws Exception;


}
