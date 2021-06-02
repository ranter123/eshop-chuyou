package com.chuyou.eshop.eshop.commodity.service;

import com.chuyou.eshop.eshop.commodity.domain.CategoryDTO;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/5/7 8:51 上午
 * @Description: 类目管理service组件
 */
public interface CategoryService {

    /**
     * 查询根类目
     * @return 根类目集合
     * @throws Exception
     */
    List<CategoryDTO> listRoots() throws Exception;

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     * @throws Exception
     */
    List<CategoryDTO> listChildren(Long id) throws Exception;

    /**
     * 新增类目
     * @param targetCategory 类目
     * @throws Exception
     */
    void save(CategoryDTO targetCategory) throws Exception;

    /**
     * 根据类目id查询类目
     * @param id 类目id
     * @return 类目
     * @throws Exception
     */
    CategoryDTO getById(Long id) throws Exception;

    /**
     * 更新类目
     * @param targetCategory 类目
     * @throws Exception
     */
    void update(CategoryDTO targetCategory) throws Exception;

    /**
     * 删除类目
     * @param id 类目id
     * @return 处理结果
     * @throws Exception
     */
    Boolean remove(Long id) throws Exception;
}
