package com.chuyou.eshop.eshop.commodity.service;

import com.chuyou.eshop.eshop.commodity.domain.PropertyDTO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyQuery;
import com.chuyou.eshop.eshop.commodity.service.impl.Properties;

import java.util.List;

/**
 * @Description: 商品属性管理模块的service组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 19:43
 */
public interface PropertyService {

    /**
     * 分页查询商品属性
     * @param propertyQuery 查询条件
     * @return 商品属性
     * @throws Exception
     */
    List<PropertyDTO> listPropertiesByPage(PropertyQuery propertyQuery) throws Exception;

    /**
     * 新增商品属性
     * @param propertyDTO 商品属性
     * @throws Exception
     */
    void saveProperty(PropertyDTO propertyDTO) throws Exception;

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     * @throws Exception
     */
    PropertyDTO getPropertyById(Long id) throws Exception;

    /**
     * 查询类目id对应的属性
     * @param categoryId 类目id
     * @return 属性
     * @throws Exception
     */
    Properties getPropertiesByCategoryId(Long categoryId) throws Exception;

    /**
     * 更新商品属性
     * @param propertyDTO 商品属性
     * @throws Exception
     */
    void updateProperty(PropertyDTO propertyDTO) throws Exception;
}
