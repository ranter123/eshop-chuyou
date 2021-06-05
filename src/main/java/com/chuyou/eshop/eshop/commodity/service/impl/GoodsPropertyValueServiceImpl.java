package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.dao.GoodsPropertyValueDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsPropertyValueDO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsPropertyValueDTO;
import com.chuyou.eshop.eshop.commodity.service.GoodsPropertyValueService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 商品属性值管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/4 16:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsPropertyValueServiceImpl implements GoodsPropertyValueService {

    /**
     * 商品属性值管理DAO组件
     */
    @Autowired
    private GoodsPropertyValueDAO propertyValueDAO;

    /**
     * 根据商品id查询属性值
     * @param goodsId 商品id
     * @return 属性值
     */
    @Override
    public List<GoodsPropertyValueDTO> listByGoodsId(Long goodsId) throws Exception {
        return ObjectUtils.convertList(propertyValueDAO.listByGoodsId(goodsId),
                GoodsPropertyValueDTO.class);
    }

    /**
     * 新增商品属性值
     * @param propertyValues 商品属性值
     */
    @Override
    public void batchSave(List<GoodsPropertyValueDTO> propertyValues) throws Exception {
        for(GoodsPropertyValueDTO propertyValue : propertyValues) {
            propertyValue.setGmtCreate(DateUtils.getCurrentTime());
            propertyValue.setGmtModified(DateUtils.getCurrentTime());
            propertyValueDAO.save(propertyValue.clone(GoodsPropertyValueDO.class));
        }
    }

    /**
     * 根据商品id删除属性值
     * @param goodsId 商品id
     */
    @Override
    public void removeByGoodsId(Long goodsId) throws Exception {
        propertyValueDAO.removeByGoodsId(goodsId);
    }
}
