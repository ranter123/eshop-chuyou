package com.chuyou.eshop.eshop.menbership.controller;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailQuery;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDetailVO;
import com.chuyou.eshop.eshop.menbership.service.MemberLevelDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 会员等级管理controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:12
 */
@RestController
@RequestMapping("/membership/member/level")
public class MemberLevelController {

    private static final Logger logger = LoggerFactory.getLogger(MemberLevelController.class);

    /**
     * 会员等级明细管理service组件
     */
    @Autowired
    private MemberLevelDetailService memberLevelDetailService;

    /**
     * 分页查询会员等级变更明细
     * @param query 查询调价你
     * @return 会员等级变更明细
     */
    @GetMapping("/")
    public List<MemberLevelDetailVO> listByPage(MemberLevelDetailQuery query) {
        try {
            return ObjectUtils.convertList(
                    memberLevelDetailService.listByPage(query),
                    MemberLevelDetailVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<MemberLevelDetailVO>();
        }
    }

}
