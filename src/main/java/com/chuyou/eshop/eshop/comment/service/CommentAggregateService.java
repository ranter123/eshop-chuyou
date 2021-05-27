package com.chuyou.eshop.eshop.comment.service;

import com.chuyou.eshop.eshop.comment.domain.CommentAggregateDTO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoDTO;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:14 下午
 * @Description: 评论统计信息管理模块的service组件接口
 */
public interface CommentAggregateService {

    /**
     * 更新评论统计信息
     * @param commentInfoDTO 评论信息
     * @throws Exception
     * @return 处理结果
     */
    CommentAggregateDTO refreshCommentAggregate(CommentInfoDTO commentInfoDTO) throws Exception;

    /**
     * 根据商品ID查询评论统计信息
     * @param goodsId 商品ID
     * @return 评论统计信息
     * @throws Exception
     */
    CommentAggregateDTO getCommentAggregateByGoodsId(Long goodsId) throws Exception;
}
