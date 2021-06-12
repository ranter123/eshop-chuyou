package com.chuyou.eshop.eshop.promotion.service.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.promotion.constant.CouponGiveOutType;
import com.chuyou.eshop.eshop.promotion.constant.CouponStatus;
import com.chuyou.eshop.eshop.promotion.dao.CouponAchieveDAO;
import com.chuyou.eshop.eshop.promotion.dao.CouponDAO;
import com.chuyou.eshop.eshop.promotion.domain.CouponAchieveDO;
import com.chuyou.eshop.eshop.promotion.domain.CouponDO;
import com.chuyou.eshop.eshop.promotion.domain.CouponDTO;
import com.chuyou.eshop.eshop.promotion.domain.CouponQuery;
import com.chuyou.eshop.eshop.promotion.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 优惠券管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl implements CouponService {
    /**
     * 优惠券管理DAO组件
     */
    @Autowired
    private CouponDAO couponDAO;
    /**
     * 优惠券领取记录管理DAO组件
     */
    @Autowired
    private CouponAchieveDAO couponAchieveDAO;
    /**
     * 会员中心接口
     */
    @Autowired
    private MembershipService membershipService;

    /**
     * 分页查询优惠券
     * @param query 查询条件
     * @return 优惠券
     */
    @Override
    public List<CouponDTO> listByPage(CouponQuery query) throws Exception {
        return ObjectUtils.convertList(couponDAO.listByPage(query), CouponDTO.class);
    }

    /**
     * 新增优惠券
     * @param coupon 优惠券
     */
    @Override
    public void save(CouponDTO coupon) throws Exception {
        coupon.setStatus(CouponStatus.UNSTARTED);
        coupon.setGmtCreate(DateUtils.getCurrentTime());
        coupon.setGmtModified(DateUtils.getCurrentTime());
        couponDAO.save(coupon.clone(CouponDO.class));
    }

    /**
     * 根据id查询优惠券
     * @param id 优惠券id
     * @return 优惠券
     */
    @Override
    public CouponDTO getById(Long id) throws Exception {
        return couponDAO.getById(id).clone(CouponDTO.class);
    }

    /**
     * 更新优惠券
     * @param coupon 优惠券
     */
    @Override
    public Boolean update(CouponDTO coupon) throws Exception {
        if(coupon.getReceivedCount() > 0L) {
            return false;
        }
        coupon.setGmtModified(DateUtils.getCurrentTime());
        couponDAO.update(coupon.clone(CouponDO.class));
        return true;
    }

    /**
     * 删除优惠券
     * @param id 优惠券id
     */
    @Override
    public Boolean remove(Long id) throws Exception {
        CouponDO coupon = couponDAO.getById(id);
        if(coupon.getReceivedCount() > 0L) {
            return false;
        }
        couponDAO.remove(id);
        return true;
    }

    /**
     * 领取优惠券
     * @param couponId 优惠券id
     * @param userAccountId 用户账号id
     * @return 是否领取成功
     * @throws Exception
     */
    @Override
    public Boolean achieve(Long couponId, Long userAccountId) throws Exception {
        CouponDO coupon = couponDAO.getById(couponId);

        if(!canAchieve(coupon)) {
            return false;
        }
        if(hasAchieved(couponId, userAccountId)) {
            return false;
        }

        createCouponAchieve(couponId, userAccountId);
        updateCouponReceivedCount(coupon, 1L);

        return true;
    }

    /**
     * 更新优惠券的领取数量
     * @param coupon 优惠券
     * @throws Exception
     */
    private void updateCouponReceivedCount(
            CouponDO coupon, Long updatedReceivedCount) throws Exception {
        coupon.setReceivedCount(coupon.getReceivedCount() + updatedReceivedCount);
        if(coupon.getReceivedCount() >= coupon.getGiveOutCount()) {
            coupon.setStatus(CouponStatus.GIVEN_OUT);
        }
        coupon.setGmtModified(DateUtils.getCurrentTime());
        couponDAO.update(coupon);
    }

    /**
     * 创建优惠券领取记录
     * @param couponId 优惠券id
     * @param userAccountId 用户账号id
     * @throws Exception
     */
    private void createCouponAchieve(Long couponId,
                                     Long userAccountId) throws Exception {
        CouponAchieveDO couponAchieve = new CouponAchieveDO();
        couponAchieve.setCouponId(couponId);
        couponAchieve.setUserAccountId(userAccountId);
        couponAchieve.setUsed(0);
        couponAchieve.setUsedTime(null);
        couponAchieve.setGmtCreate(DateUtils.getCurrentTime());
        couponAchieve.setGmtModified(DateUtils.getCurrentTime());

        couponAchieveDAO.save(couponAchieve);
    }

    /**
     * 判断优惠券能否领取
     * @param coupon 优惠券
     * @return 能否领取
     * @throws Exception
     */
    private Boolean canAchieve(CouponDO coupon) throws Exception {
        Boolean isGivingOutStatus = CouponStatus.GIVING_OUT.equals(coupon.getStatus());
        Boolean isAchievableGiveOutType = CouponGiveOutType.ACHIEVABLE_AND_GIVE_OUT.equals(coupon.getGiveOutType())
                || CouponGiveOutType.ONLY_ACHIEVABLE.equals(coupon.getGiveOutType());
        return isGivingOutStatus && isAchievableGiveOutType;
    }

    /**
     * 判断用户是否已经领取过这个优惠券了
     * @param couponId 优惠券id
     * @param userAccountId 用户账号id
     * @return 是否已经领取过这个优惠券了
     * @throws Exception
     */
    private Boolean hasAchieved(Long couponId,
                                Long userAccountId) throws Exception {
        CouponAchieveDO couponAchieve = couponAchieveDAO.getByUserAccountId(
                couponId, userAccountId);
        return couponAchieve != null ? true : false;
    }

    /**
     * 发放优惠券
     * @param couponId 优惠券id
     * @return 是否发放成功
     * @throws Exception
     */
    @Override
    public Boolean giveOut(Long couponId) throws Exception {
        CouponDO coupon = couponDAO.getById(couponId);

        if(!canGiveOut(coupon)) {
            return false;
        }

        giveOutForAllUserAccount(coupon);
        updateCouponReceivedCount(coupon, coupon.getGiveOutCount());

        return true;
    }

    /**
     * 为所有用户发放优惠券
     * @param coupon 优惠券
     * @throws Exception
     */
    private void giveOutForAllUserAccount(CouponDO coupon) throws Exception {
        List<UserAccountDTO> userAccounts = membershipService.listAllUserAccounts();
        for(UserAccountDTO userAccount : userAccounts) {
            createCouponAchieve(coupon.getId(), userAccount.getId());
        }
    }

    /**
     * 判断优惠券能否发放
     * @param coupon 优惠券
     * @return 能否领取
     * @throws Exception
     */
    private Boolean canGiveOut(CouponDO coupon) throws Exception {
        Boolean isGivingOutStatus = CouponStatus.GIVING_OUT.equals(coupon.getStatus());
        Boolean isGiveOutType = CouponGiveOutType.ACHIEVABLE_AND_GIVE_OUT.equals(coupon.getGiveOutType())
                || CouponGiveOutType.ONLY_GIVE_OUT.equals(coupon.getGiveOutType());
        return isGivingOutStatus && isGiveOutType;
    }
}
