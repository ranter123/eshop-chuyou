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
}
