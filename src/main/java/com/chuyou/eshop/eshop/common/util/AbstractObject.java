package com.chuyou.eshop.eshop.common.util;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 8:04 下午
 * @Description: 基础POJO类
 */
public class AbstractObject {

    /**
     * 浅克隆
     * @param clazz 克隆类
     * @param <T> 泛型
     * @return 克隆对象
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T clone(Class<T> clazz) throws Exception {
        T target = clazz.newInstance();
        BeanCopierUtils.copyProperties(this, target);
        return target;
    }

    /**
     * 浅克隆
     * @param target 克隆目标对象
     * @param <T> 泛型
     * @return 克隆目标对象
     */
    public <T> T clone(T target) {
        BeanCopierUtils.copyProperties(this, target);
        return target;
    }

    /**
     * 深度克隆
     * @param clazz 克隆类
     * @param cloneDirection 克隆方向
     * @param <T> 泛型
     * @return 克隆对象
     */
    public <T> T clone(Class<T> clazz, Integer cloneDirection) throws Exception {
        //浅克隆
        T targetObject = clazz.newInstance();
        BeanCopierUtils.copyProperties(this, targetObject);

        //完成所有List深度克隆
        Class<?> thisClazz = this.getClass();
        Field[] fields = thisClazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            //如果判断某个字段是List类型的
            if (field.getType() != List.class) {
                continue;
            }

            List<?> list = (List<?>) field.get(this);
            if (list == null || list.size() == 0) {
                continue;
            }

            //获取list集合中的泛型类型
            //RelationDTO
            Class<?> listGenericClazz = getListGenericType(field);

            //获取要克隆的目标类型
            Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericClazz, cloneDirection);

            //将一个list克隆到另一个list
            List clonedList = new ArrayList();
            clonedList(list, clonedList, cloneTargetClazz, cloneDirection);

            //获取设置克隆好的list的方法名称
            Method setFieldMethod = getSetCloneListFieldMethod(field, clazz);
            setFieldMethod.invoke(targetObject, clonedList);
        }
        return targetObject;
    }

    /**
     * 获取设置克隆好的list的方法名称
     * @param field 字段
     * @param clazz 类
     * @param <T> 泛型
     * @return 方法
     */
    private <T> Method getSetCloneListFieldMethod(Field field, Class<T> clazz) {
        String name = field.getName();
        String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method setCloneListFieldMethod = null;
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(setMethodName)) {
                setCloneListFieldMethod = method;
                break;
            }
        }
        return setCloneListFieldMethod;
    }

    /**
     * 将一个list克隆到另一个list
     * @param sourceList 源list
     * @param targetList 目标list
     * @param cloneTargetClazz 克隆目标类
     * @param cloneDirection 克隆方向
     */
    private void clonedList(List<?> sourceList, List targetList, Class<?> cloneTargetClazz, Integer cloneDirection)
            throws Exception {
        for (Object object : sourceList) {
            AbstractObject targetObject = (AbstractObject) object;
            AbstractObject clonedObject = (AbstractObject) targetObject.clone(cloneTargetClazz, cloneDirection);
            targetList.add(clonedObject);
        }
    }

    /**
     * 获取目标类名
     * @param listGenericClazz 集合类
     * @param cloneDirection 克隆方向
     * @return 目标类名
     */
    private Class<?> getCloneTargetClazz(Class<?> listGenericClazz, Integer cloneDirection)
            throws ClassNotFoundException {
        String cloneTargetClassName = null;

        String className = listGenericClazz.getName();
        if (cloneDirection.equals(CloneDirection.FORWARD)) {
            if (className.endsWith(DomainType.VO)) {
                cloneTargetClassName = className.substring(0, className.length() - 2) + "DTO";
            } else if (className.endsWith(DomainType.DTO)) {
                cloneTargetClassName = className.substring(0, className.length() - 3) + "DO";
            }
        }

        if (cloneDirection.equals(CloneDirection.OPPOSITE)) {
            if (className.endsWith(DomainType.DTO)) {
                cloneTargetClassName = className.substring(0, className.length() - 3) + "VO";
            } else if (className.endsWith(DomainType.DO)) {
                cloneTargetClassName = className.substring(0, className.length() - 2) + "DTO";
            }
        }
        return Class.forName(cloneTargetClassName);
    }

    /**
     * 获取list集合的泛型类型
     * @param field list集合字段
     * @return list集合泛型类型
     */
    private Class<?> getListGenericType(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }
}
