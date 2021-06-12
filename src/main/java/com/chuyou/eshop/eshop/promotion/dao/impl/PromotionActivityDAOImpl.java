package com.chuyou.eshop.eshop.promotion.dao.impl;

import com.chuyou.eshop.eshop.promotion.dao.PromotionActivityDAO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityQuery;
import com.chuyou.eshop.eshop.promotion.mapper.PromotionActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 促销活动管理DAO组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 14:54
 */
@Repository
public class PromotionActivityDAOImpl implements PromotionActivityDAO {

    /**
     * 促销活动管理mapper组件
     */
    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    /**
     * 分页查询促销活动
     * @param query 查询条件
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDO> listByPage(PromotionActivityQuery query) {
        return promotionActivityMapper.listByPage(query);
    }

    /**
     * 根据id查询促销活动
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDO getById(Long id) {
        return promotionActivityMapper.getById(id);
    }

    /**
     * 查询全部促销活动
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDO> listAll() {
        return promotionActivityMapper.listAll();
    }

    /**
     * 查询指定商品目前可以使用的启用状态的促销活动
     * @param goodsId 商品id
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDO> listEnabledByGoodsId(Long goodsId) {
        return promotionActivityMapper.listEnabledByGoodsId(goodsId);
    }

    /**
     * 新增促销活动
     * @param activity 促销活动
     */
    @Override
    public Long save(PromotionActivityDO activity) {
        promotionActivityMapper.save(activity);
        return activity.getId();
    }

    /**
     * 更新促销活动
     * @param activity 促销活动
     */
    @Override
    public void update(PromotionActivityDO activity) {
        promotionActivityMapper.update(activity);
    }

    /**
     * 更新促销活动的状态
     * @param activity 促销活动
     */
    @Override
    public void updateStatus(PromotionActivityDO activity) {
        promotionActivityMapper.updateStatus(activity);
    }

    /**
     * 删除促销活动
     * @param id 促销活动id
     */
    @Override
    public void remove(Long id) {
        promotionActivityMapper.remove(id);
    }


}
