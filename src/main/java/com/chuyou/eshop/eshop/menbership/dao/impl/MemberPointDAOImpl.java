package com.chuyou.eshop.eshop.menbership.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.menbership.dao.MemberPointDAO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDO;
import com.chuyou.eshop.eshop.menbership.mapper.MemberPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Description: 会员积分管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 17:21
 */
@Repository
public class MemberPointDAOImpl implements MemberPointDAO {

    /**
     * 会员积分管理mapper组件
     */
    @Autowired
    private MemberPointMapper memberPointMapper;

    /**
     * 根据用户账号id查询会员积分
     * @param userAccountId 用户账号id
     * @return 会员积分
     */
    @Override
    public MemberPointDO getByUserAccountId(Long userAccountId) throws Exception {
        MemberPointDO memberPoint = memberPointMapper.getByUserAccountId(userAccountId);

        if(memberPoint == null) {
            memberPoint = new MemberPointDO();
            memberPoint.setUserAccountId(userAccountId);
            memberPoint.setPoint(0L);
            save(memberPoint);
        }

        return memberPoint;
    }

    /**
     * 新增会员积分
     * @param memberPoint 会员积分
     */
    @Override
    public void save(MemberPointDO memberPoint) throws Exception {
        memberPoint.setGmtCreate(DateUtils.getCurrentTime());
        memberPoint.setGmtModified(DateUtils.getCurrentTime());
        memberPointMapper.save(memberPoint);
    }

    /**
     * 更新会员积分
     * @param memberPoint 会员积分
     */
    @Override
    public void update(MemberPointDO memberPoint) throws Exception {
        memberPoint.setGmtModified(DateUtils.getCurrentTime());
        memberPointMapper.update(memberPoint);
    }
}
