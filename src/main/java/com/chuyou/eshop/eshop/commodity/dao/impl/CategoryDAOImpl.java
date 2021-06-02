package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.CategoryDAO;
import com.chuyou.eshop.eshop.commodity.domain.CategoryDO;
import com.chuyou.eshop.eshop.commodity.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/5/7 8:53 上午
 * @Description: 类目管理DAO组件
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询根目录
     * @return 根类目集合
     * @throws Exception
     */
    @Override
    public List<CategoryDO> listRoots() throws Exception {
        return categoryMapper.listRoots();
    }

    /**
     * 查询子目录
     * @param id 父类目id
     * @return 子类目集合
     * @throws Exception
     */
    @Override
    public List<CategoryDO> listChildren(Long id) throws Exception {
        return categoryMapper.listChildren(id);
    }

    /**
     * 新增类目
     * @param category 类目
     * @return 类目id
     * @throws Exception
     */
    @Override
    public Long save(CategoryDO category) throws Exception {
        categoryMapper.save(category);
        return category.getId();
    }

    /**
     * 根据id查询类目
     * @param id 类目id
     * @return 类目
     * @throws Exception
     */
    @Override
    public CategoryDO getById(Long id) throws Exception {
        return categoryMapper.getById(id);
    }

    /**
     * 更新类目
     * @param category 类目
     * @throws Exception
     */
    @Override
    public void update(CategoryDO category) throws Exception {
        categoryMapper.update(category);
    }

    /**
     * 删除类目
     * @param id 类目id
     * @throws Exception
     */
    @Override
    public void remove(Long id) throws Exception {
        categoryMapper.remove(id);
    }
}
