package com.chuyou.eshop.eshop.menbership.service;

import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailDTO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailQuery;

import java.util.List;

/**
 * @Description: 会员积分明细管理service接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:17
 */
public interface MemberPointDetailService {

    /**
     * 分页查询会员积分变更明细
     * @param query 查询调价你
     * @return 会员积分变更明细
     * @throws Exception
     */
    List<MemberPointDetailDTO> listByPage(MemberPointDetailQuery query) throws Exception;

}
