package com.chuyou.eshop.eshop.promotion.service.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.promotion.constant.CouponStatus;
import com.chuyou.eshop.eshop.promotion.dao.CouponAchieveDAO;
import com.chuyou.eshop.eshop.promotion.dao.CouponDAO;
import com.chuyou.eshop.eshop.promotion.dao.PromotionActivityDAO;
import com.chuyou.eshop.eshop.promotion.domain.CouponAchieveDO;
import com.chuyou.eshop.eshop.promotion.domain.CouponDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import com.chuyou.eshop.eshop.promotion.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 促销中心service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 14:49
 */
@Component
public class PromotionServiceImpl implements PromotionService {

    private static final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

    /**
     * 促销活动管理DAO组件
     */
    @Autowired
    private PromotionActivityDAO promotionActivityDAO;
    /**
     * 优惠券领取记录管理DAO组件
     */
    @Autowired
    private CouponAchieveDAO couponAchieveDAO;
    /**
     * 优惠券管理DAO组件
     */
    @Autowired
    private CouponDAO couponDAO;

    /**
     * 根据商品id查询促销活动
     * @param goodsId 商品id
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDTO> listByGoodsId(Long goodsId) {
        try {
            return ObjectUtils.convertList(promotionActivityDAO.listEnabledByGoodsId(goodsId),
                    PromotionActivityDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<PromotionActivityDTO>();
        }
    }

    /**
     * 根据id查询促销活动
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDTO getById(Long id) {
        try {
            return promotionActivityDAO.getById(id).clone(PromotionActivityDTO.class);
        } catch(Exception e) {
            logger.error("Error", e);
            return null;
        }
    }

    /**
     * 查询用户当前可以使用的有效优惠券
     * @param userAccountId 用户账号id
     * @return 有效优惠券
     */
    @Override
    public List<CouponDTO> listValidByUserAccountId(Long userAccountId) {
        List<CouponDTO> coupons = new ArrayList<CouponDTO>();

        try {
            List<CouponAchieveDO> couponAchieves = couponAchieveDAO
                    .listUnsedByUserAccountId(userAccountId);
            for(CouponAchieveDO couponAchieve : couponAchieves) {
                CouponDTO coupon = couponDAO.getById(couponAchieve.getCouponId())
                        .clone(CouponDTO.class);
                if(CouponStatus.GIVING_OUT.equals(coupon.getStatus()) ||
                        CouponStatus.GIVEN_OUT.equals(coupon.getStatus())) {
                    coupons.add(coupon);
                }
            }
        } catch (Exception e) {
            logger.error("error", e);
        }

        return coupons;
    }

    /**
     * 使用优惠券
     * @param couponId 优惠券id
     * @param userAccountId 用户账号id
     * @return 处理结果
     */
    @Override
    public Boolean useCoupon(Long couponId, Long userAccountId) {
        try {
            CouponAchieveDO couponAchieve = new CouponAchieveDO();
            couponAchieve.setCouponId(couponId);
            couponAchieve.setUserAccountId(userAccountId);
            couponAchieve.setUsed(1);
            couponAchieve.setUsedTime(DateUtils.getCurrentTime());
            couponAchieve.setGmtModified(DateUtils.getCurrentTime());

            couponAchieveDAO.update(couponAchieve);

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

}
