package com.chuyou.eshop.eshop.menbership.service.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.menbership.dao.DeliveryAddressDAO;
import com.chuyou.eshop.eshop.menbership.domain.DeliveryAddressDO;
import com.chuyou.eshop.eshop.menbership.domain.DeliveryAddressDTO;
import com.chuyou.eshop.eshop.menbership.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 收货地址管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    /**
     * 收货地址管理DAO组件
     */
    @Autowired
    private DeliveryAddressDAO deliveryAddressDAO;

    /**
     * 查询用户账号的所有收货地址
     *
     * @param userAccountId 用户账号id
     * @return 所有收货地址
     */
    @Override
    public List<DeliveryAddressDTO> listAllByUserAccountId(
            Long userAccountId) throws Exception {
        return ObjectUtils.convertList(deliveryAddressDAO.listAllByUserAccountId(userAccountId),
                DeliveryAddressDTO.class);
    }

    /**
     * 新增收货地址
     *
     * @param deliveryAddress 收货地址
     */
    @Override
    public void save(DeliveryAddressDTO deliveryAddress) throws Exception {
        deliveryAddress.setGmtCreate(DateUtils.getCurrentTime());
        deliveryAddress.setGmtModified(DateUtils.getCurrentTime());
        deliveryAddressDAO.save(deliveryAddress.clone(DeliveryAddressDO.class));
    }

    /**
     * 更新收货地址
     *
     * @param deliveryAddress 收货地址
     */
    @Override
    public void update(DeliveryAddressDTO deliveryAddress) throws Exception {
        deliveryAddress.setGmtModified(DateUtils.getCurrentTime());
        deliveryAddressDAO.update(deliveryAddress.clone(DeliveryAddressDO.class));
    }

    /**
     * 删除收货地址
     *
     * @param id 收货地址id
     */
    @Override
    public void remove(Long id) throws Exception {
        deliveryAddressDAO.remove(id);
    }
}