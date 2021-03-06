package com.chuyou.eshop.eshop.common.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/4/7 8:56 上午
 * @Description: spring容器组件
 */
@Component
public class SpringApplicationContext {

    /**
     * spring容器
     */
    private ApplicationContext applicationContext;

    /**
     * 构造函数
     * @param context spring容器
     */
    @Autowired
    public SpringApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    /**
     * 获取bean
     * @param clazz bean类型
     * @param <T>
     * @return bean实例
     */
    public<T> T getBean(Class<? extends T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
