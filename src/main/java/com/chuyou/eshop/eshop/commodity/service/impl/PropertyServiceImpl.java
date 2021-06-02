package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.dao.PropertyDAO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyDO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyDTO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyQuery;
import com.chuyou.eshop.eshop.commodity.service.PropertyService;
import com.chuyou.eshop.eshop.common.bean.SpringApplicationContext;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品属性管理模块的service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 19:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PropertyServiceImpl implements PropertyService {

    /**
     * 商品属性管理模块的DAO组件
     */
    @Autowired
    private PropertyDAO propertyDAO;

    /**
     * spring容器
     */
    @Autowired
    private SpringApplicationContext context;

    /**
     * 分页查询商品属性
     * @param propertyQuery 查询条件
     * @return 商品属性
     * @throws Exception
     */
    @Override
    public List<PropertyDTO> listPropertiesByPage(PropertyQuery propertyQuery) throws Exception {
        List<PropertyDO> propertyDOs = propertyDAO.listPropertiesByPage(propertyQuery);
        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for (PropertyDO propertyDO : propertyDOs) {
            propertyDTOs.add(propertyDO.clone(PropertyDTO.class));
        }
        return propertyDTOs;
    }

    /**
     * 新增商品属性
     * @param propertyDTO 商品属性
     * @throws Exception
     */
    @Override
    public void saveProperty(PropertyDTO propertyDTO) throws Exception {
        propertyDTO.setGmtCreate(DateUtils.getCurrentTime());
        propertyDTO.setGmtModified(DateUtils.getCurrentTime());
        PropertyDO propertyDO = propertyDTO.clone(PropertyDO.class);
        propertyDAO.saveProperty(propertyDO);
    }

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     * @throws Exception
     */
    @Override
    public PropertyDTO getPropertyById(Long id) throws Exception {
        PropertyDO propertyDO = propertyDAO.getPropertyById(id);
        return propertyDO.clone(PropertyDTO.class);
    }

    /**
     * 查询类目id对应的属性
     * @param categoryId 类目id
     * @return 属性
     * @throws Exception
     */
    @Override
    public Properties getPropertiesByCategoryId(Long categoryId) throws Exception {
        CategoryOperation<Properties> operation = context.getBean(QueryPropertyCategoryOperation.class);
        Category category = new Category(categoryId);
        return category.execute(operation);
    }

    /**
     * 更新商品属性
     * @param propertyDTO 商品属性
     * @throws Exception
     */
    @Override
    public void updateProperty(PropertyDTO propertyDTO) throws Exception {
        propertyDTO.setGmtModified(DateUtils.getCurrentTime());
        PropertyDO propertyDO = propertyDTO.clone(PropertyDO.class);
        propertyDAO.updateProperty(propertyDO);
    }
}
