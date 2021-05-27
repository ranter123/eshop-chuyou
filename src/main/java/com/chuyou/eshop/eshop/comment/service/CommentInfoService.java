package com.chuyou.eshop.eshop.comment.service;

import com.chuyou.eshop.eshop.comment.domain.CommentInfoDTO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoQuery;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:07 下午
 * @Description: 评论管理模块service组件
 */
public interface CommentInfoService {

    /**
     * 分页查询评论信息
     * @param query 评论查询条件
     * @return 评论信息
     */
    List<CommentInfoDTO> listByPage(CommentInfoQuery query) throws Exception;

    /**
     * 根据id查询评论情况
     * @param id 评论信息id
     * @return 评论信息
     */
    CommentInfoDTO getById(Long id) throws Exception;

    /**
     * 更新评论
     * @param comment 评论信息
     */
    void update(CommentInfoDTO comment) throws Exception;

    /**
     * 删除评论
     * @param id 评论id
     */
    void remove(Long id);

    /**
     * 新增手动发表的评论信息
     * @param commentInfoDTO 评论信息DTO对象
     * @throws Exception
     */
    void saveManualPublishedCommentInfo(CommentInfoDTO commentInfoDTO) throws Exception;
}
