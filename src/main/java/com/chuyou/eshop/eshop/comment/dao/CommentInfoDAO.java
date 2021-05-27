package com.chuyou.eshop.eshop.comment.dao;

import com.chuyou.eshop.eshop.comment.domain.CommentInfoDO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoQuery;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:09 下午
 * @Description: 评论管理模块DAO组件
 */
public interface CommentInfoDAO {

    /**
     * 分页查询评论信息
     * @param query 评论信息查询条件
     * @return 评论信息
     */
    List<CommentInfoDO> listByPage(CommentInfoQuery query);

    /**
     * 根据id查询评论信息
     * @param id 评论信息id
     * @return 评论信息
     */
    CommentInfoDO getById(Long id);

    /**
     * 更新评论
     * @param targetComment 评论信息
     */
    void update(CommentInfoDO targetComment);

    /**
     * 删除评论
     * @param id 评论id
     */
    void remove(Long id);

    /**
     * 新增评论信息
     * @param commentInfoDO 评论信息DO对象
     * @throws Exception
     */
    Long saveCommentInfo(CommentInfoDO commentInfoDO) throws Exception;
}
