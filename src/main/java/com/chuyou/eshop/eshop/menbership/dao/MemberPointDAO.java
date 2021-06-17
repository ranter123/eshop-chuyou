package com.chuyou.eshop.eshop.menbership.dao;

import com.chuyou.eshop.eshop.menbership.domain.MemberPointDO;

/**
 * @Description: 会员积分管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 17:17
 */
public interface MemberPointDAO {

    /**
     * 根据用户账号id查询会员积分
     * @param userAccountId 用户账号id
     * @return 会员积分
     * @throws Exception
     */
    MemberPointDO getByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 新增会员积分
     * @param memberPoint 会员积分
     * @throws Exception
     */
    void save(MemberPointDO memberPoint) throws Exception;

    /**
     * 更新会员积分
     * @param memberPoint 会员积分
     * @throws Exception
     */
    void update(MemberPointDO memberPoint) throws Exception;


}
