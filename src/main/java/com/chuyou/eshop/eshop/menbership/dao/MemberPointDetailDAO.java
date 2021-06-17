package com.chuyou.eshop.eshop.menbership.dao;

import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailDO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailQuery;

import java.util.List;

/**
 * @Description: 会员积分变更明细管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:18
 */
public interface MemberPointDetailDAO {

    /**
     * 分页查询会员积分变更明细
     * @param query 查询调价你
     * @return 会员积分变更明细
     * @throws Exception
     */
    List<MemberPointDetailDO> listByPage(MemberPointDetailQuery query) throws Exception;

    /**
     * 新增会员积分明细
     * @param memberPointDetail 会员积分明细
     * @throws Exception
     */
    void save(MemberPointDetailDO memberPointDetail) throws Exception;

}
