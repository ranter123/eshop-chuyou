package com.chuyou.eshop.eshop.menbership.dao;

import com.chuyou.eshop.eshop.menbership.domain.DeliveryAddressDO;

import java.util.List;

/**
 * @Description: 收货地址管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:10
 */
public interface DeliveryAddressDAO {

    /**
     * 查询用户账号的所有收货地址
     * @param userAccountId 用户账号id
     * @return 所有收货地址
     */
    List<DeliveryAddressDO> listAllByUserAccountId(Long userAccountId);

    /**
     * 新增收货地址
     * @param deliveryAddress 收货地址
     */
    void save(DeliveryAddressDO deliveryAddress);

    /**
     * 更新收货地址
     * @param deliveryAddress 收货地址
     */
    void update(DeliveryAddressDO deliveryAddress);

    /**
     * 删除收货地址
     * @param id 收货地址id
     */
    void remove(Long id);

}
