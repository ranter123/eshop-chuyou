package com.chuyou.eshop.eshop.menbership.service;

import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailDTO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailQuery;

import java.util.List;

/**
 * @Description: 会员等级明细管理service接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:14
 */
public interface MemberLevelDetailService {

    /**
     * 分页查询会员等级变更明细
     * @param query 查询调价你
     * @return 会员等级变更明细
     * @throws Exception
     */
    List<MemberLevelDetailDTO> listByPage(MemberLevelDetailQuery query) throws Exception;

}
