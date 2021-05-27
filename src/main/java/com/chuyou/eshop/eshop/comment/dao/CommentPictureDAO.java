package com.chuyou.eshop.eshop.comment.dao;

import com.chuyou.eshop.eshop.comment.domain.CommentPictureDO;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:17 下午
 * @Description: 评论嗮图DAO组件
 */
public interface CommentPictureDAO {

    /**
     * 根据评论信息id查询图片
     * @param commentId 评论信息id
     * @return 评论图片
     */
    List<CommentPictureDO> listByCommentId(Long commentId);

    /**
     * 新增评论嗮图
     * @param commentPictureDO 评论晒图DO对象
     * @throws Exception
     * @return 评论晒图id
     */
    Long saveCommentPicture(CommentPictureDO commentPictureDO) throws Exception;

    /**
     * 根据ID查询图片
     * @param id 评论图片ID
     * @return 评论图片
     * @throws Exception
     */
    CommentPictureDO getById(Long id) throws Exception;
}
