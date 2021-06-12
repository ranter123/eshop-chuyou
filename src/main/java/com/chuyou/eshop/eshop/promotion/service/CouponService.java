package com.chuyou.eshop.eshop.promotion.service;

import com.chuyou.eshop.eshop.promotion.domain.CouponDTO;
import com.chuyou.eshop.eshop.promotion.domain.CouponQuery;

import java.util.List;

/**
 * @Description: 优惠券管理service接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:22
 */
public interface CouponService {

    /**
     * 分页查询优惠券
     * @param query 查询条件
     * @return 优惠券
     * @throws Exception
     */
    List<CouponDTO> listByPage(CouponQuery query) throws Exception;

    /**
     * 新增优惠券
     * @param coupon 优惠券
     * @throws Exception
     */
    void save(CouponDTO coupon) throws Exception;

    /**
     * 根据id查询优惠券
     * @param id 优惠券id
     * @return 优惠券
     * @throws Exception
     */
    CouponDTO getById(Long id) throws Exception;

    /**
     * 更新优惠券
     * @param coupon 优惠券
     * @return 处理结果
     * @throws Exception
     */
    Boolean update(CouponDTO coupon) throws Exception;

    /**
     * 删除优惠券
     * @param id 优惠券id
     * @return 处理结果
     * @throws Exception
     */
    Boolean remove(Long id) throws Exception;

    /**
     * 领取优惠券
     * @param couponId 优惠券id
     * @param userAccountId 用户账号id
     * @return 是否领取成功
     * @throws Exception
     */
    Boolean achieve(Long couponId, Long userAccountId) throws Exception;

    /**
     * 发放优惠券
     * @param couponId 优惠券id
     * @return 是否发放成功
     * @throws Exception
     */
    Boolean giveOut(Long couponId) throws Exception;


}
