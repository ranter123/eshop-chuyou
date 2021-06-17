package com.chuyou.eshop.eshop.menbership.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.menbership.dao.MemberPointDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailDO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailQuery;
import com.chuyou.eshop.eshop.menbership.mapper.MemberPointDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 会员积分变更明细管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:19
 */
@Repository
public class MemberPointDetailDAOImpl implements MemberPointDetailDAO {

    /**
     * 会员积分明细管理mapper组件
     */
    @Autowired
    private MemberPointDetailMapper memberPointDetailMapper;

    /**
     * 分页查询会员积分变更明细
     * @param query 查询调价你
     * @return 会员积分变更明细
     */
    @Override
    public List<MemberPointDetailDO> listByPage(MemberPointDetailQuery query) throws Exception {
        return memberPointDetailMapper.listByPage(query);
    }

    /**
     * 新增会员积分明细
     * @param memberPointDetail 会员积分明细
     */
    @Override
    public void save(MemberPointDetailDO memberPointDetail) throws Exception {
        memberPointDetail.setGmtCreate(DateUtils.getCurrentTime());
        memberPointDetail.setGmtModified(DateUtils.getCurrentTime());
        memberPointDetailMapper.save(memberPointDetail);
    }
}
