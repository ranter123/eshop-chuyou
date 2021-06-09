package com.chuyou.eshop.eshop.common.json;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @Description: json字段提取器
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/9 17:27
 */
@Component
public class JsonExtractor {

    /**
     * 获取表达式对应的值
     * @param targetJson 目标json对象
     * @param jsonExtractSyntax 表达式
     * @return 值
     * @throws Exception
     */
    public String getString(JSONObject targetJson, String jsonExtractSyntax) throws Exception{
        return String.valueOf(get(targetJson, jsonExtractSyntax));
    }

    /**
     * 获取表达式对应的值
     * @param targetJson 目标json对象
     * @param jsonExtractSyntax 表达式
     * @return 值
     * @throws Exception
     */
    public Double getDouble(JSONObject targetJson, String jsonExtractSyntax) throws Exception{
        return Double.valueOf(String.valueOf(get(targetJson, jsonExtractSyntax)));
    }

    /**
     * 获取表达式对应的值
     * @param targetJson 目标json对象
     * @param jsonExtractSyntax 表达式
     * @return 值
     * @throws Exception
     */
    public Long getLong(JSONObject targetJson, String jsonExtractSyntax) throws Exception{
        return Long.valueOf(String.valueOf(get(targetJson, jsonExtractSyntax)));
    }

    /**
     * 获取表达式对应的值
     * @param targetJson 目标json对象
     * @param jsonExtractSyntax 表达式
     * @return 值
     * @throws Exception
     */
    public Integer getInteger(JSONObject targetJson, String jsonExtractSyntax) throws Exception{
        return Integer.valueOf(String.valueOf(get(targetJson, jsonExtractSyntax)));
    }

    /**
     * 获取json表达式字段值
     * @param targetJson 目标json
     * @param jsonExtractSyntax json表达是
     * @return json表达式字段值
     * @throws Exception
     */
    public Object get(JSONObject targetJson, String jsonExtractSyntax) throws Exception {
        JsonExpression expression = JsonExtractSyntaxParser.parse(jsonExtractSyntax);
        return expression.interpret(new JsonExpressionContext(targetJson));
    }
}
