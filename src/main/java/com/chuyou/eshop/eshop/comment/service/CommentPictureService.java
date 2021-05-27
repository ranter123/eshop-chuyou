package com.chuyou.eshop.eshop.comment.service;

import com.chuyou.eshop.eshop.comment.domain.CommentPictureDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:13 下午
 * @Description: 评论晒图管理模块service组件
 */
public interface CommentPictureService {

    /**
     * 根据评论信息id查询图片
     * @param id 评论信息id
     * @return 评论图片
     */
    List<CommentPictureDTO> listByCommentId(Long id) throws Exception;

    /**
     * 保存评论晒图
     * @param appBasePath 当前应用根路径
     * @param commentInfoId 评论信息id
     * @param files 评论晒图
     * @throws Exception
     */
    void saveCommentPictures(String appBasePath, Long commentInfoId, MultipartFile[] files) throws Exception;

    /**
     * 根据ID查询图片
     * @param id 评论图片ID
     * @return 评论图片
     * @throws Exception
     */
    CommentPictureDTO getById(Long id) throws Exception;
}
