package com.chuyou.eshop.eshop.menbership.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.menbership.dao.MemberLevelDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailDO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailQuery;
import com.chuyou.eshop.eshop.menbership.mapper.MemberLevelDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 会员等级变更明细管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:15
 */
@Repository
public class MemberLevelDetailDAOImpl implements MemberLevelDetailDAO {

    /**
     * 会员等级明细管理mapper组件
     */
    @Autowired
    private MemberLevelDetailMapper memberLevelDetailMapper;

    /**
     * 分页查询会员等级变更明细
     * @param query 查询调价你
     * @return 会员等级变更明细
     */
    @Override
    public List<MemberLevelDetailDO> listByPage(MemberLevelDetailQuery query) throws Exception {
        return memberLevelDetailMapper.listByPage(query);
    }

    /**
     * 新增会员等级明细
     * @param memberLevelDetail 会员等级明细
     */
    @Override
    public void save(MemberLevelDetailDO memberLevelDetail) throws Exception {
        memberLevelDetail.setGmtCreate(DateUtils.getCurrentTime());
        memberLevelDetail.setGmtModified(DateUtils.getCurrentTime());
        memberLevelDetailMapper.save(memberLevelDetail);
    }
}
