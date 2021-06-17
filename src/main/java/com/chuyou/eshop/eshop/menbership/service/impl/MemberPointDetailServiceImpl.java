package com.chuyou.eshop.eshop.menbership.service.impl;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.menbership.dao.MemberPointDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailDTO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDetailQuery;
import com.chuyou.eshop.eshop.menbership.service.MemberPointDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 会员积分明细管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberPointDetailServiceImpl implements MemberPointDetailService {

    /**
     * 会员积分明细管理DAO组件
     */
    @Autowired
    private MemberPointDetailDAO memberPointDetailDAO;

    /**
     * 分页查询会员积分变更明细
     * @param query 查询调价你
     * @return 会员积分变更明细
     */
    @Override
    public List<MemberPointDetailDTO> listByPage(
            MemberPointDetailQuery query) throws Exception {
        return ObjectUtils.convertList(memberPointDetailDAO.listByPage(query),
                MemberPointDetailDTO.class);
    }


}
