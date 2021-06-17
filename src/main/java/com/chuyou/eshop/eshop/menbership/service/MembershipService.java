package com.chuyou.eshop.eshop.menbership.service;

import com.chuyou.eshop.eshop.menbership.domain.UserAccountDTO;

import java.util.List;

/**
 * @Description: 会员中心对外提供的接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 11:23
 */
public interface MembershipService {

    /**
     * 通知会员中心，“用户今日第一次登录”事件发生了
     * @param userAccountId 用户账号ID
     * @return 处理结果
     */
    Boolean informFirstLoginDailyEvent(Long userAccountId);

    /**
     * 通知会员中心，“支付订单”事件发生了
     * @param userAccountId 用户账号id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     */
    Boolean informPayOrderEvent(Long userAccountId, Double totalOrderAmount);

    /**
     * 通知会员中心，“完成退货”事件发生了
     * @param userAccountId 用户账号id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     */
    Boolean informFinishReturnGoodsEvent(Long userAccountId, Double totalOrderAmount);

    /**
     * 通知会员中心，“发表评论”事件发生了
     * @param userAccountId 用户账号id
     * @param showPictures 是否晒图
     * @return 处理结果
     */
    Boolean informPublishCommentEvent(Long userAccountId, Boolean showPictures);

    /**
     * 通知会员中心，“删除评论”事件发生了
     * @param userAccountId 用户账号id
     * @param showPictures 是否晒图
     * @return 处理结果
     */
    Boolean informRemoveCommentEvent(Long userAccountId, Boolean showPictures);

    /**
     * 查询所有的用户账户
     * @return
     */
    List<UserAccountDTO> listAllUserAccounts();
}
