package com.chuyou.eshop.eshop.commodity.controller;

import com.chuyou.eshop.eshop.commodity.domain.PropertyDTO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyQuery;
import com.chuyou.eshop.eshop.commodity.domain.PropertyVO;
import com.chuyou.eshop.eshop.commodity.service.PropertyService;
import com.chuyou.eshop.eshop.commodity.service.impl.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品属性管理模块的controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 19:23
 */
@RestController
@RequestMapping("/commodity/property")
public class PropertyController {

    private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

    /**
     * 商品属性管理模块的service组件
     */
    @Autowired
    private PropertyService propertyService;

    /**
     * 分页查询商品属性
     * @param propertyQuery 商品属性查询条件
     * @return 商品属性
     */
    @GetMapping("/")
    public List<PropertyVO> listPropertiesByPage(PropertyQuery propertyQuery) {
        try {
            List<PropertyDTO> propertyDTOs = propertyService.listPropertiesByPage(propertyQuery);
            List<PropertyVO> propertyVOs = new ArrayList<>();
            for (PropertyDTO propertyDTO : propertyDTOs) {
                propertyVOs.add(propertyDTO.clone(PropertyVO.class));
            }
            return propertyVOs;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     */
    @GetMapping("/{id}")
    public PropertyVO getPropertyById(@PathVariable("id") Long id) {
        try {
            PropertyDTO propertyDTO = propertyService.getPropertyById(id);
            return propertyDTO.clone(PropertyVO.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new PropertyVO();
    }

    /**
     * 新增商品属性
     * @param propertyVO 商品属性VO对象
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean saveProperty(@RequestBody PropertyVO propertyVO) {
        try {
            PropertyDTO propertyDTO = propertyVO.clone(PropertyDTO.class);
            propertyService.saveProperty(propertyDTO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新商品属性
     * @param propertyVO 商品属性VO对象
     * @return 处理结果
     */
    @PutMapping("/{id}")
    public Boolean updateProperty(@RequestBody PropertyVO propertyVO) {
        try {
            PropertyDTO propertyDTO = propertyVO.clone(PropertyDTO.class);
            propertyService.updateProperty(propertyDTO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 查询类目id对应的所有属性
     * @param categoryId 类目id
     * @return
     */
    @GetMapping("/all/{categoryId}")
    public Properties getPropertiesByCategoryId(@PathVariable("categoryId") Long categoryId) {
        try {
            return propertyService.getPropertiesByCategoryId(categoryId);
        } catch (Exception e) {
            logger.error("error", e);
            return new Properties();
        }
    }
}
