package com.chuyou.eshop.eshop.promotion.controller;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.promotion.domain.CouponDTO;
import com.chuyou.eshop.eshop.promotion.domain.CouponQuery;
import com.chuyou.eshop.eshop.promotion.domain.CouponVO;
import com.chuyou.eshop.eshop.promotion.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 优惠券管理controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:22
 */
@RestController
@RequestMapping("/promotion/coupon")
public class CouponController {

    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    /**
     * 优惠券管理service组件
     */
    @Autowired
    private CouponService couponService;

    /**
     * 分页查询优惠券
     * @param query 查询条件
     * @return 优惠券
     */
    @GetMapping("/")
    public List<CouponVO> listByPage(CouponQuery query) {
        try {
            return ObjectUtils.convertList(couponService.listByPage(query), CouponVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<CouponVO>();
        }
    }

    /**
     * 新增优惠券
     * @param coupon 优惠券
     */
    @PostMapping("/")
    public Boolean save(@RequestBody CouponVO coupon) {
        try {
            couponService.save(coupon.clone(CouponDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("eror", e);
            return false;
        }
    }

    /**
     * 根据id查询优惠券
     * @param id 优惠券id
     * @return 优惠券
     */
    @GetMapping("/{id}")
    public CouponVO getById(@PathVariable("id") Long id) {
        try {
            return couponService.getById(id).clone(CouponVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new CouponVO();
        }
    }

    /**
     * 更新优惠券
     * @param coupon 优惠券
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody CouponVO coupon) {
        try {
            return couponService.update(coupon.clone(CouponDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除优惠券
     * @param id 优惠券id
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            return couponService.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 领取优惠券
     * @param couponId 优惠券id
     * @param userAccountId 用户账号id
     * @return 是否领取成功
     * @throws Exception
     */
    @PutMapping("/achieve")
    public Boolean achieve(Long couponId, Long userAccountId) {
        try {
            return couponService.achieve(couponId, userAccountId);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 发放优惠券
     * @param couponId 优惠券id
     * @return 是否领取成功
     * @throws Exception
     */
    @PutMapping("/giveOut")
    public Boolean giveOut(Long couponId) {
        try {
            return couponService.giveOut(couponId);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }


}
