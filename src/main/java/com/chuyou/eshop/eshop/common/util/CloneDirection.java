package com.chuyou.eshop.eshop.common.util;

/**
 * @Author: ranter
 * @Date: 2021/4/6 10:15 下午
 * @Description: 克隆方向
 */
public class CloneDirection {

    /**
     * 正向克隆：从VO -> DTO, DTO -> DO
     */
    public static final Integer FORWARD = 1;

    /**
     * 反向克隆：从DTO -> VO, DO -> DTO
     */
    public static final Integer OPPOSITE = 2;
}
