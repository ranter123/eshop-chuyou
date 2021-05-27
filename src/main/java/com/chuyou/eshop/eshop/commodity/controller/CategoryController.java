package com.chuyou.eshop.eshop.commodity.controller;

import com.chuyou.eshop.eshop.commodity.domain.CategoryDTO;
import com.chuyou.eshop.eshop.commodity.domain.CategoryVO;
import com.chuyou.eshop.eshop.commodity.service.CategoryService;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/5/7 8:51 上午
 * @Description: 类目管理controller组件
 */
@RestController
@RequestMapping("/commodity/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * 类目管理service组件
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询根类目
     * @return 跟类目集合
     */
    @GetMapping("/root")
    public List<CategoryVO> listRoots() {
        try {
            List<CategoryDTO> categories = categoryService.listRoots();
            List<CategoryVO> resultCategories = ObjectUtils.convertList(categories, CategoryVO.class);
            return resultCategories;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }
}
