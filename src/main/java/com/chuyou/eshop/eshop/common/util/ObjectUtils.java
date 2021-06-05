package com.chuyou.eshop.eshop.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 7:57 下午
 * @Description: 对象工具类
 */
public class ObjectUtils {

    /**
     * 集合转换
     * @param sourceList 原集合
     * @param targetClazz 目标集合
     * @param <T> 转换目标类
     * @return 目标集合
     * @throws Exception
     */
    public static <T> List<T> convertList(List<? extends AbstractObject> sourceList,
                                          Class<T> targetClazz) throws Exception {
        if (sourceList == null) {
            return null;
        }
        List<T> targetList = new ArrayList<T>();
        for (AbstractObject sourceObject : sourceList) {
            targetList.add(sourceObject.clone(targetClazz));
        }
        return targetList;
    }

    /**
     * 集合转换以
     * @param sourceList 原集合
     * @param targetClazz 目标集合
     * @param direction 转换常量
     * @param <T> 转换目标类
     * @return 目标集合
     */
    public static <T> List<T> convertList(List<? extends AbstractObject> sourceList,
                                          Class<T> targetClazz,
                                          Integer direction) throws Exception {
        if (sourceList == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (AbstractObject object : sourceList) {
            result.add(object.clone(targetClazz, direction));
        }
        return result;
    }
}
