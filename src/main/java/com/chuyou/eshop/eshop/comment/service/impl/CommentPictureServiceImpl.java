package com.chuyou.eshop.eshop.comment.service.impl;

import com.chuyou.eshop.eshop.comment.constant.CommentPictureUploadDirType;
import com.chuyou.eshop.eshop.comment.dao.CommentPictureDAO;
import com.chuyou.eshop.eshop.comment.domain.CommentPictureDO;
import com.chuyou.eshop.eshop.comment.domain.CommentPictureDTO;
import com.chuyou.eshop.eshop.comment.service.CommentPictureService;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:14 下午
 * @Description: 评论嗮图管理模块的service组件
 */
@Service
public class CommentPictureServiceImpl implements CommentPictureService {

    /**
     * 评论晒图管理DAO组件
     */
    @Autowired
    private CommentPictureDAO commentPictureDAO;

    /**
     * 评论晒图的上传目录
     */
    @Value("${comment.picture.upload.dir}")
    private String uploadDirPath;

    @Value("${comment.picture.upload.dir.type}")
    private String uploadDirType;

    /**
     * 根据评论信息id查询图片
     * @param commentId 评论信息id
     * @return 评论图片
     */
    @Override
    public List<CommentPictureDTO> listByCommentId(Long commentId) throws Exception {
        List<CommentPictureDO> pictures = commentPictureDAO.listByCommentId(commentId);
        List<CommentPictureDTO> resultPictures = ObjectUtils.convertList(pictures, CommentPictureDTO.class);
        return resultPictures;
    }

    /**
     * 保存评论晒图
     * @param appBasePath 当前应用根路径
     * @param commentInfoId 评论信息id
     * @param files 评论晒图
     * @throws Exception
     */
    @Override
    public void saveCommentPictures(String appBasePath,
                                    Long commentInfoId, MultipartFile[] files) throws Exception {
        // 处理上传目录
        String realUploadDirPath = uploadDirPath;

        if (CommentPictureUploadDirType.RELATIVE.equals(uploadDirType)) {
            realUploadDirPath = appBasePath + uploadDirPath;
        }

        // 将图片上传到指定的目录中去
        File uploadDir = new File(realUploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file == null) {
                continue;
            }

            // 如果目标文件路径已经存在，则需要删除目标文件
            String fileSeparator = System.getProperties().getProperty("file.separator");
            String targetFilePath = realUploadDirPath + fileSeparator + file.getOriginalFilename();
            File targetFile = new File(targetFilePath);
            if (targetFile.exists()) {
                targetFile.delete();
            }

            // 将上传上来的文件保存到指定的文件中去
            file.transferTo(targetFile);

            // 保存评论晒图信息
            CommentPictureDO commentPictureDO = new CommentPictureDO();
            commentPictureDO.setCommentInfoId(commentInfoId);
            commentPictureDO.setCommentPicturePath(targetFilePath);
            commentPictureDO.setGmtCreate(new Date());
            commentPictureDO.setGmtModified(new Date());

            commentPictureDAO.saveCommentPicture(commentPictureDO);
        }
    }

    /**
     * 根据ID查询图片
     * @param id 评论图片ID
     * @return 评论图片
     * @throws Exception
     */
    @Override
    public CommentPictureDTO getById(Long id) throws Exception {
        CommentPictureDO picture = commentPictureDAO.getById(id);
        CommentPictureDTO resultPicture = picture.clone(CommentPictureDTO.class);
        return resultPicture;
    }
}
