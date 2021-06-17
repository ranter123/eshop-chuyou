package com.chuyou.eshop.eshop.menbership.dao;

import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailDO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailQuery;

import java.util.List;

/**
 * @Description: 会员等级变更明细管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:15
 */
public interface MemberLevelDetailDAO {

    /**
     * 分页查询会员等级变更明细
     * @param query 查询调价你
     * @return 会员等级变更明细
     * @throws Exception
     */
    List<MemberLevelDetailDO> listByPage(MemberLevelDetailQuery query) throws Exception;

    /**
     * 新增会员等级明细
     * @param memberLevelDetail 会员等级明细
     * @throws Exception
     */
    void save(MemberLevelDetailDO memberLevelDetail) throws Exception;

}
