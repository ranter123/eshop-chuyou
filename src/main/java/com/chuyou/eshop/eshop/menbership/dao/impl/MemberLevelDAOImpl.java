package com.chuyou.eshop.eshop.menbership.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.menbership.constant.MemberLevel;
import com.chuyou.eshop.eshop.menbership.dao.MemberLevelDAO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDO;
import com.chuyou.eshop.eshop.menbership.mapper.MemberLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Description: 会员等级管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 17:21
 */
@Repository
public class MemberLevelDAOImpl implements MemberLevelDAO {

    /**
     * 会员等级管理mapper组件
     */
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /**
     * 根据用户账号id查询会员等级
     * @param userAccountId 用户账号id
     * @return 会员等级
     */
    @Override
    public MemberLevelDO getByUserAccountId(Long userAccountId) throws Exception {
        MemberLevelDO memberLevel = memberLevelMapper.getByUserAccountId(userAccountId);

        if(memberLevel == null) {
            memberLevel = new MemberLevelDO();
            memberLevel.setUserAccountId(userAccountId);
            memberLevel.setGrowthValue(0L);
            memberLevel.setLevel(MemberLevel.BRONZE);
            save(memberLevel);
        }

        return memberLevel;
    }

    /**
     * 新增会员等级
     * @param memberLevel 会员等级
     */
    @Override
    public void save(MemberLevelDO memberLevel) throws Exception {
        memberLevel.setGmtCreate(DateUtils.getCurrentTime());
        memberLevel.setGmtModified(DateUtils.getCurrentTime());
        memberLevelMapper.save(memberLevel);
    }

    /**
     * 更新会员等级
     * @param memberLevel 会员等级
     */
    @Override
    public void update(MemberLevelDO memberLevel) throws Exception {
        memberLevel.setGmtModified(DateUtils.getCurrentTime());
        memberLevelMapper.update(memberLevel);
    }


}
