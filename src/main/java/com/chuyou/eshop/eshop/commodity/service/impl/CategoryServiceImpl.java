package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.dao.*;
import com.chuyou.eshop.eshop.commodity.domain.*;
import com.chuyou.eshop.eshop.commodity.service.CategoryService;
import com.chuyou.eshop.eshop.common.bean.SpringApplicationContext;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: ranter
 * @Date: 2021/5/7 8:52 上午
 * @Description: 类目管理service组件
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * 类目管理DAO组件
     */
    @Autowired
    private CategoryDAO categoryDAO;

    /**
     * 类目属性关系dao组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO categoryPropertyRelationDAO;

    /**
     * 属性分组DAO组件
     */
    @Autowired
    private PropertyGroupDAO propertyGroupDAO;

    /**
     * 属性分组与属性关系DAO组件
     */
    @Autowired
    private PropertyGroupRelationshipDAO propertyGroupRelationDAO;

    /**
     * 属性管理模块DAO组件
     */
    @Autowired
    private PropertyDAO propertyDAO;

    /**
     * spring容器组件
     */
    @Autowired
    private SpringApplicationContext context;

    /**
     * 查询根类目
     * @return 根类目集合
     * @throws Exception
     */
    @Override
    public List<CategoryDTO> listRoots() throws Exception {
        List<CategoryDO> categories = categoryDAO.listRoots();
        List<CategoryDTO> resultCategories = ObjectUtils.convertList(categories, CategoryDTO.class);
        return resultCategories;
    }

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     * @throws Exception
     */
    @Override
    public List<CategoryDTO> listChildren(Long id) throws Exception {
        List<CategoryDO> categories = categoryDAO.listChildren(id);
        List<CategoryDTO> resultCategories = ObjectUtils.convertList(categories, CategoryDTO.class);
        return resultCategories;
    }

    /**
     * 新增类目
     * @param targetCategory 类目
     * @throws Exception
     */
    @Override
    public void save(CategoryDTO targetCategory) throws Exception {
        saveCategory(targetCategory);
        saveCategoryPropertyRelations(targetCategory);
        savePropertyGroup(targetCategory);
    }


    /**
     * 保存属性分组
     * @param targetCategory 类目
     */
    private void savePropertyGroup(CategoryDTO targetCategory) throws Exception{
        if (targetCategory.getPropertyGroups() == null ||
                targetCategory.getPropertyGroups().size() == 0) {
            return;
        }
        for (PropertyGroupDTO group : targetCategory.getPropertyGroups()) {
            group.setCategoryId(targetCategory.getId());
            group.setGmtCreate(DateUtils.getCurrentTime());
            group.setGmtModified(DateUtils.getCurrentTime());
            Long groupId = propertyGroupDAO.save(group.clone(PropertyGroupDO.class));
            group.setId(groupId);
            savePropertyGroupRelations(group);
        }
    }

    /**
     * 保存属性分组与属性的关联关系
     * @param group 属性分组
     */
    private void savePropertyGroupRelations(PropertyGroupDTO group) throws Exception{
        if (group.getRelations() == null || group.getRelations().size() == 0) {
            return;
        }

        for (PropertyGroupRelationshipDTO relation : group.getRelations()) {
            relation.setPropertyGroupId(group.getId());
            relation.setGmtCreate(DateUtils.getCurrentTime());
            relation.setGmtModified(DateUtils.getCurrentTime());

            propertyGroupRelationDAO.save(relation.clone(PropertyGroupRelationshipDO.class));
        }
    }

    /**
     * 保存类目与属性之间的关联关系
     * @param targetCategory 类目
     * @throws Exception
     */
    private void saveCategoryPropertyRelations(CategoryDTO targetCategory) throws Exception {
        if (targetCategory.getPropertyRelations() == null ||
                targetCategory.getPropertyRelations().size() == 0) {
            return;
        }

        for (CategoryPropertyRelationshipDTO relation : targetCategory.getPropertyRelations()) {
            relation.setCategoryId(targetCategory.getId());
            relation.setGmtCreate(DateUtils.getCurrentTime());
            relation.setGmtModified(DateUtils.getCurrentTime());

            categoryPropertyRelationDAO.save(relation.clone(CategoryPropertyRelationshipDO.class));
        }

    }

    /**
     * 保存类目的基本信息
     * @param targetCategory 类目
     */
    private void saveCategory(CategoryDTO targetCategory) throws Exception{
        targetCategory.setGmtCreate(DateUtils.getCurrentTime());
        targetCategory.setGmtModified(DateUtils.getCurrentTime());
        Long categoryId = categoryDAO.save(targetCategory.clone(CategoryDO.class));
        targetCategory.setId(categoryId);
    }

    /**
     * 根据类目id查询类目
     * @param id 类目id
     * @return 类目
     * @throws Exception
     */
    @Override
    public CategoryDTO getById(Long id) throws Exception {
        // 查询类目基本信息
        CategoryDTO category = categoryDAO.getById(id).clone(CategoryDTO.class);

        // 查询类目与属性的关联关系
        List<CategoryPropertyRelationshipDO> relations =
                categoryPropertyRelationDAO.listByCategoryId(id);
        category.setPropertyRelations(ObjectUtils.convertList(relations, CategoryPropertyRelationshipDTO.class));

        // 查询类目关联的属性
        List<PropertyDO> properties = new ArrayList<>();
        for (CategoryPropertyRelationshipDO relation : relations) {
            properties.add(propertyDAO.getPropertyById(relation.getPropertyId()));
        }
        category.setProperties(ObjectUtils.convertList(properties, PropertyDTO.class));

        // 查询类目关联的属性分组
        List<PropertyGroupDTO> propertyGroups = getPropertyGroupByCategoryId(id);
        category.setPropertyGroups(propertyGroups);
        return category;
    }

    /**
     * 根据类目id查询属性分组
     * @param categoryId 类目id
     * @return 属性分组
     * @throws Exception
     */
    private List<PropertyGroupDTO> getPropertyGroupByCategoryId(Long categoryId) throws Exception{
        List<PropertyGroupDTO> resultPropertyGroups = new ArrayList<>();

        // 查询类目关联的属性分组
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(categoryId);

        // 查询属性分组与属性的关联关系，以及属性分组关联的属性
        for (PropertyGroupDO propertyGroup : propertyGroups) {
            PropertyGroupDTO resultPropertyGroup = propertyGroup.clone(PropertyGroupDTO.class);
            List<PropertyGroupRelationshipDO> relations =
                    propertyGroupRelationDAO.listByPropertyGroupId(propertyGroup.getId());
            resultPropertyGroup.setRelations(ObjectUtils.convertList(relations, PropertyGroupRelationshipDTO.class));
            List<PropertyDTO> properties = new ArrayList<>();
            for (PropertyGroupRelationshipDO relation : relations) {
                properties.add(getPropertyById(relation.getPropertyId()));
            }
            resultPropertyGroup.setProperties(properties);
            resultPropertyGroups.add(resultPropertyGroup);
        }
        return resultPropertyGroups;
    }

    /**
     * 根据id查询属性
     * @param propertyId 属性id
     * @return
     * @throws Exception
     */
    private PropertyDTO getPropertyById(Long propertyId) throws Exception{
        PropertyDO property = propertyDAO.getPropertyById(propertyId);
        return property.clone(PropertyDTO.class);
    }

    /**
     * 更新类目
     * @param category 类目
     * @throws Exception
     */
    @Override
    public void update(CategoryDTO category) throws Exception {
        updateCategory(category);
        removeCategoryPropertyRelations(category);
        saveCategoryPropertyRelations(category);

        removePropertyGroupRelations(category);
        savePropertyGroup(category);
    }

    /**
     * 删除类目的属性分组与属性的关联关系
     * @param category 类目
     * @throws Exception
     */
    private void removePropertyGroupRelations(CategoryDTO category) throws Exception{
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(category.getId());

        for (PropertyGroupDO propertyGroup : propertyGroups) {
            propertyGroupRelationDAO.removeByPropertyGroupId(propertyGroup.getId());
        }

        propertyGroupDAO.removeByCategoryId(category.getId());
    }

    /**
     * 删除类目与属性的关联关系
     * @param category 类目
     * @throws Exception
     */
    private void removeCategoryPropertyRelations(CategoryDTO category) throws Exception {
        categoryPropertyRelationDAO.removeByCategoryId(category.getId());
    }

    /**
     * 更新类目
     * @param category 类目
     */
    private void updateCategory(CategoryDTO category) throws Exception {
        category.setGmtModified(DateUtils.getCurrentTime());
        categoryDAO.update(category.clone(CategoryDO.class));
    }

    /**
     * 删除类目
     * @param id 类目id
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean remove(Long id) throws Exception {
        Category category = new Category();
        CategoryOperation<Boolean> relatedCheckOperation = context.getBean(RelatedCheckCategoryOperation.class);
        Boolean result = category.execute(relatedCheckOperation);
        if (result) {
            return false;
        }
        CategoryOperation<Boolean> removeOperation =
                context.getBean(RemoveCategoryOperation.class);

        return category.execute(removeOperation);
    }
}
