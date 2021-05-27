package com.chuyou.eshop.eshop.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 7:57 下午
 * @Description: 对象工具类
 */
public class ObjectUtils {

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
}
