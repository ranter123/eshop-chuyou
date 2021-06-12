package com.chuyou.eshop.eshop.promotion.dao;

import com.chuyou.eshop.eshop.promotion.domain.CouponDO;
import com.chuyou.eshop.eshop.promotion.domain.CouponQuery;

import java.util.List;

/**
 * @Description: 优惠券管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:27
 */
public interface CouponDAO {

    /**
     * 分页查询优惠券
     * @param query 查询条件
     * @return 优惠券
     */
    List<CouponDO> listByPage(CouponQuery query);

    /**
     * 新增优惠券
     * @param coupon 优惠券
     */
    void save(CouponDO coupon);

    /**
     * 根据id查询优惠券
     * @param id 优惠券id
     * @return 优惠券
     */
    CouponDO getById(Long id);

    /**
     * 更新优惠券
     * @param coupon 优惠券
     */
    void update(CouponDO coupon);

    /**
     * 删除优惠券
     * @param id 优惠券id
     */
    void remove(Long id);

    /**
     * 查询所有优惠券
     * @return 所有优惠券
     */
    List<CouponDO> listAll();

    /**
     * 更新优惠券状态
     * @param coupon 优惠券
     */
    void updateStatus(CouponDO coupon);


}
