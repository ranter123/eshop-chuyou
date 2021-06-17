package com.chuyou.eshop.eshop.menbership.service.impl;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.menbership.dao.MemberLevelDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailDTO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailQuery;
import com.chuyou.eshop.eshop.menbership.service.MemberLevelDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: 会员等级明细管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:14
 */
public class MemberLevelDetailServiceImpl implements MemberLevelDetailService {

    /**
     * 会员等级明细管理DAO组件
     */
    @Autowired
    private MemberLevelDetailDAO memberLevelDetailDAO;

    /**
     * 分页查询会员等级变更明细
     * @param query 查询调价你
     * @return 会员等级变更明细
     */
    @Override
    public List<MemberLevelDetailDTO> listByPage(
            MemberLevelDetailQuery query) throws Exception {
        return ObjectUtils.convertList(memberLevelDetailDAO.listByPage(query),
                MemberLevelDetailDTO.class);
    }

}
