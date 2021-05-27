package com.chuyou.eshop.eshop.auth.service.impl;

/**
 * @Author: ranter
 * @Date: 2021/4/2 7:46 下午
 * @Description: 权限操作
 */
public interface PriorityOperation<T> {

    /**
     * 执行这个操作
     * @param priority 权限
     * @return 处理结果
     */
    T doExecute(Priority priority) throws Exception;
}
