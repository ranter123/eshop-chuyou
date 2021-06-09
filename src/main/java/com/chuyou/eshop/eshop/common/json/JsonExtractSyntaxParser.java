package com.chuyou.eshop.eshop.common.json;

/**
 * @Description: json语法解析器
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/8 20:01
 */
public class JsonExtractSyntaxParser {

    /**
     * json语法解析分隔符
     */
    private static final String JSON_EXTRACT_SYNTAX_PARSER_SPLIT = "/";

    public static JsonExpression parse(String jsonExtractSyntax) throws Exception {
        JsonExpression rootExpression = null;
        CompositionJsonExpression currentJsonExpression = null;
        String[] elements = jsonExtractSyntax.split(JSON_EXTRACT_SYNTAX_PARSER_SPLIT);
        for (int i = 0; i < elements.length; i ++) {
            if (i == 0) {
                if (elements.length == 1) {
                    rootExpression = new LeftJsonExpression(elements[i]);
                } else {
                    rootExpression = new CompositionJsonExpression(elements[i]);
                    currentJsonExpression = (CompositionJsonExpression) rootExpression;
                }
            } else if (i < elements.length - 1) {
                CompositionJsonExpression childJsonExpression = new CompositionJsonExpression(elements[i]);
                currentJsonExpression.setChildJsonExpression(childJsonExpression);
                currentJsonExpression = childJsonExpression;
            } else {
                LeftJsonExpression leftJsonExpression = new LeftJsonExpression(elements[i]);
                currentJsonExpression.setChildJsonExpression(leftJsonExpression);
            }
        }
        return rootExpression;
    }
}
