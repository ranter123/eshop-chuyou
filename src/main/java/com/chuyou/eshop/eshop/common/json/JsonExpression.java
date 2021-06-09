package com.chuyou.eshop.eshop.common.json;

/**
 * @Description: json表达式接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/8 19:39
 */
public interface JsonExpression {

    /**
     * 解释表达式
     * @param context json上下文
     * @return 结果
     */
    Object interpret(JsonExpressionContext context);
}
