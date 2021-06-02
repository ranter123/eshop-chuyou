package com.chuyou.eshop.eshop.commodity.controller;

import com.chuyou.eshop.eshop.commodity.domain.*;
import com.chuyou.eshop.eshop.commodity.service.CategoryService;
import com.chuyou.eshop.eshop.common.util.CloneDirection;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询子类目
     * @return 子类目集合
     */
    @GetMapping("/children/{id}")
    public List<CategoryVO> listChildren(@PathVariable("id") Long id) {
        try {
            List<CategoryDTO> categories = categoryService.listChildren(id);
            List<CategoryVO> resultCategories = ObjectUtils.convertList(categories, CategoryVO.class);
            return resultCategories;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 新增类目
     * @param category 类目
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean save(@RequestBody CategoryVO category) {
        try {
            CategoryDTO targetCategory = category.clone(CategoryDTO.class);
            List<CategoryPropertyRelationshipDTO> targetPropertyRelations = ObjectUtils.convertList(
                    category.getPropertyRelations(), CategoryPropertyRelationshipDTO.class);
            targetCategory.setPropertyRelations(targetPropertyRelations);

            //转换分组
            if (category.getPropertyGroups() != null) {
                List<PropertyGroupDTO> targetPropertyGroups = new ArrayList<>();
                targetCategory.setPropertyGroups(targetPropertyGroups);
                for (PropertyGroupVO group : category.getPropertyGroups()) {
                    PropertyGroupDTO targetGroup = group.clone(PropertyGroupDTO.class);
                    targetGroup.setRelations(ObjectUtils.convertList(group.getRelations(),
                            PropertyGroupRelationshipDTO.class));
                    targetPropertyGroups.add(targetGroup);
                }
            }

            categoryService.save(targetCategory);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据id查询类目
     * @param id 类目id
     * @return 类目
     */
    @GetMapping("/{id}")
    public CategoryVO getById(@PathVariable("id") Long id) {
        try {
            CategoryDTO category = categoryService.getById(id);
            CategoryVO resultCategory = category.clone(CategoryVO.class, CloneDirection.OPPOSITE);
            return resultCategory;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 更新类目
     * @param category 类目
     * @return 处理结果
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody CategoryVO category) {
        try {
            CategoryDTO targetCategory = category.clone(CategoryDTO.class, CloneDirection.FORWARD);
            categoryService.update(targetCategory);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除类目
     * @param id 类目id
     * @return 处理结果
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            return categoryService.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }


}
