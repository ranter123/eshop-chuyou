package com.chuyou.eshop.eshop.comment.controller;

import com.chuyou.eshop.eshop.comment.constant.CommentApproved;
import com.chuyou.eshop.eshop.comment.constant.CommentStatus;
import com.chuyou.eshop.eshop.comment.constant.ShowPictures;
import com.chuyou.eshop.eshop.comment.domain.*;
import com.chuyou.eshop.eshop.comment.service.CommentAggregateService;
import com.chuyou.eshop.eshop.comment.service.CommentInfoService;
import com.chuyou.eshop.eshop.comment.service.CommentPictureService;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:04 下午
 * @Description: 评论管理模块controller组件
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    /**
     * 评论管理模块service组件
     */
    @Autowired
    private CommentInfoService commentInfoService;

    /**
     * 评论嗮图管理模块的service组件
     */
    @Autowired
    private CommentPictureService commentPictureService;

    /**
     * 评论统计信息管理模块的service组件
     */
    @Autowired
    private CommentAggregateService commentAggregateService;

    /**
     * 手动发表评论
     * @param request http请求
     * @param commentInfoVO 评论信息
     * @param files 评论图片
     * @return 评论结果
     */
    @PostMapping("/")
    public Boolean publishComment(HttpServletRequest request,
                                  CommentInfoVO commentInfoVO, MultipartFile[] files) {
        try {
            // 为评论设置是否晒图
            Integer showPictures = ShowPictures.NO;

            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (file != null) {
                        showPictures = ShowPictures.YES;
                        break;
                    }
                }
            }
            commentInfoVO.setShowPictures(showPictures);

            // 保存评论信息
            CommentInfoDTO commentInfoDTO = commentInfoVO.clone(CommentInfoDTO.class);
            commentInfoService.saveManualPublishedCommentInfo(commentInfoDTO);

            // 上传评论图片
            String appBasePath = request.getSession().getServletContext().getRealPath("/");
            commentPictureService.saveCommentPictures(appBasePath, commentInfoDTO.getId(), files);

            // 更新评论统计信息
            commentAggregateService.refreshCommentAggregate(commentInfoDTO);

            // todo 通知订单中心以及发表了评论


            // todo 通知会员中心以及发表了评论

        } catch (Exception e) {
            logger.error("error:" , e);
            return false;
        }
        return true;
    }

    /**
     * 分页查询评论信息
     * @param query 查询条件
     * @return 评论信息
     */
    @GetMapping("/")
    public List<CommentInfoVO> listByPage(@RequestBody CommentInfoQuery query) {
        try {
            List<CommentInfoDTO> comments = commentInfoService.listByPage(query);
            List<CommentInfoVO> resultComments = ObjectUtils.convertList(comments, CommentInfoVO.class);
            return resultComments;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<CommentInfoVO>();
        }
    }

    /**
     * 根据id查询评论信息
     * @param id 评论信息id
     * @return 评论信息
     */
    @GetMapping("/{id}")
    public CommentInfoVO getById(@PathVariable("id") Long id) {
        try {
            CommentInfoDTO comment = commentInfoService.getById(id);
            CommentInfoVO resultComment = comment.clone(CommentInfoVO.class);

            List<CommentPictureDTO> pictures = commentPictureService.listByCommentId(id);
            List<CommentPictureVO> resultPictures = ObjectUtils.convertList(pictures, CommentPictureVO.class);

            resultComment.setPictures(resultPictures);

            return resultComment;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 显示图片
     * @param id 图片id
     */
    @GetMapping("/picture/{id}")
    public void viewPicture(@PathVariable("id") Long id,
                            HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;

        try {
            CommentPictureDTO picture = commentPictureService.getById(id);
            File file = new File(picture.getCommentPicturePath());
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            response.setContentType("image/jpg");
            OutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        } catch (Exception e) {
            logger.error("error", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    logger.error("error", e);
                }
            }
        }
    }

    /**
     * 审核评论
     * @param id 评论id
     * @param approved 操作
     * @return 审核结果
     */
    @PutMapping("/approve/{id}")
    public Boolean update(@PathVariable("id") Long id, Integer approved) {
        try {
            CommentInfoDTO comment = new CommentInfoDTO();
            comment.setId(id);
            comment.setCommentStatus(CommentApproved.YES.equals(approved) ?
                    CommentStatus.APPROVED : CommentStatus.APPROVE_REJECTED);
            commentInfoService.update(comment);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除评论
     * @param id 评论id
     * @return 处理结果
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            commentInfoService.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 在前台展示评论信息
     * @param goodsId 商品id
     * @param query 查询条件
     * @return
     */
    @GetMapping("/show/{goodsId}")
    public CommentShowVO show(@PathVariable("goodsId") Long goodsId, CommentInfoQuery query) {
        try {
            // 构造评论展示VO对象
            CommentShowVO commentShow = new CommentShowVO();
            commentShow.setGoodsId(goodsId);

            // 查询评论统计信息
            CommentAggregateDTO aggregate = commentAggregateService.getCommentAggregateByGoodsId(goodsId);
            commentShow.setAggregate(aggregate.clone(CommentAggregateVO.class));

            // 查询评论列表
            query.setCommentStatus(CommentStatus.APPROVED);

            List<CommentInfoVO> targetComments = new ArrayList<>();

            List<CommentInfoDTO> comments = commentInfoService.listByPage(query);
            for (CommentInfoDTO comment : comments) {
                CommentInfoVO targetComment = comment.clone(CommentInfoVO.class);
                targetComment.setUsername(getEncryptedUsername(targetComment.getUsername()));

                List<CommentPictureDTO> pictures = commentPictureService.listByCommentId(comment.getId());

                targetComment.setPictures(ObjectUtils.convertList(pictures, CommentPictureVO.class));
                targetComments.add(targetComment);
            }

            commentShow.setComments(targetComments);

            return commentShow;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 获取加密后的用户名
     * @param username 用户名
     * @return 加密后的用户名
     */
    private String getEncryptedUsername(String username) {
        StringBuilder builder = new StringBuilder("");

        builder.append(username.substring(0, 1));
        for (int i = 1; i < username.length() - 1; i ++) {
            builder.append("*");
        }
        builder.append(username.substring(username.length() - 1));
        return builder.toString();
    }
}
