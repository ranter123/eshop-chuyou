package com.chuyou.eshop.eshop.promotion.dao;

import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityGoodsRelationDO;

import java.util.List;

/**
 * @Description: 促销活动与商品关联关系管理的DAO组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:35
 */
public interface PromotionActivityGoodsRelationDAO {

    /**
     * 根据促销活动id查询促销活动与商品的关联关系
     * @param promotionActivityId 促销活动id
     * @return 促销活动与商品的关联关系
     */
    List<PromotionActivityGoodsRelationDO> listByActivityId(
            Long promotionActivityId);

    /**
     * 新增促销活动与商品的关联关系
     * @param relation 促销活动与商品的关联关系
     */
    void save(PromotionActivityGoodsRelationDO relation);

    /**
     * 删除促销活动对应的与商品的关联关系
     * @param promotionActivityId 促销活动id
     */
    void removeByActivityId(Long promotionActivityId);


}
