package com.chuyou.eshop.eshop.promotion.dao;

import com.chuyou.eshop.eshop.promotion.domain.CouponAchieveDO;

import java.util.List;

/**
 * @Description: 优惠券领取记录管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:32
 */
public interface CouponAchieveDAO {

    /**
     * 根据优惠券id和用户账号id查询优惠券的领取记录
     * @param couponId 优惠券id
     * @param userAccountId 用户账号id
     * @return 优惠券领取记录
     */
    CouponAchieveDO getByUserAccountId(Long couponId, Long userAccountId);

    /**
     * 新增优惠券领取记录
     * @param couponAchieve 优惠券领取记录
     */
    void save(CouponAchieveDO couponAchieve);

    /**
     * 查询用户还没有使用过的优惠券领取记录
     * @param userAccountId 用户账号id
     * @return 优惠券领取记录
     */
    List<CouponAchieveDO> listUnsedByUserAccountId(Long userAccountId);

    /**
     * 更新优惠券领取记录
     * @param couponAchieve 优惠券领取记录
     */
    void update(CouponAchieveDO couponAchieve);


}
