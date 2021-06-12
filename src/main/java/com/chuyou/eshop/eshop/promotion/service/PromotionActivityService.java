package com.chuyou.eshop.eshop.promotion.service;

import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityQuery;

import java.util.List;

/**
 * @Description: 促销活动管理service组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:30
 */
public interface PromotionActivityService {

    /**
     * 分页查询促销活动
     * @param query 查询条件
     * @return 促销活动
     * @throws Exception
     */
    List<PromotionActivityDTO> listByPage(PromotionActivityQuery query) throws Exception;

    /**
     * 根据id查询促销活动
     * @param id 促销活动id
     * @return 促销活动
     * @throws Exception
     */
    PromotionActivityDTO getById(Long id) throws Exception;

    /**
     * 新增促销活动
     * @param activity 促销活动
     * @throws Exception
     */
    void save(PromotionActivityDTO activity) throws Exception;

    /**
     * 更新促销活动
     * @param activity 促销活动
     * @throws Exception
     */
    void update(PromotionActivityDTO activity) throws Exception;

    /**
     * 删除促销活动
     * @param id 促销活动id
     * @throws Exception
     */
    void remove(Long id) throws Exception;


}
