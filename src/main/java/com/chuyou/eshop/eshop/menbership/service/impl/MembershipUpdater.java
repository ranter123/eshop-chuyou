package com.chuyou.eshop.eshop.menbership.service.impl;

/**
 * @Description: 会员信息更新组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 16:02
 */
public interface MembershipUpdater<T, K> {

    /**
     * 执行会员信息更新操作
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 返回值
     * @throws Exception
     */
    K execute(Long userAccountId, T parameter) throws Exception;

    /**
     * 撤销会员信息更新操作
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 返回值
     * @throws Exception
     */
    K undo(Long userAccountId, T parameter) throws Exception;
}
