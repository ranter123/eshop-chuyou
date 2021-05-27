package com.chuyou.eshop.eshop.common.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @Author: ranter
 * @Date: 2021/5/5 11:03 下午
 * @Description: 文件工具类
 */
public class FileUtils {

    /**
     * 根据相对路径获取一个绝对路径
     * @param relativePath 相对路径
     * @return 绝对路径
     * @throws Exception
     */
    public static String getPathByRelative(String relativePath) throws Exception {
        File appBaseDir = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!appBaseDir.exists()) {
            appBaseDir = new File("");
        }
        File targetDir = new File(appBaseDir.getAbsolutePath(), relativePath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        return targetDir.getAbsolutePath();
    }

    /**
     * 上传文件
     * @param file 文件
     * @param uploadDirPath 上传目录的路径
     * @return 上传文件的绝对路径
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file, String uploadDirPath) throws Exception{
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String pathSeparator = System.getProperties().getProperty("file.separator");
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String filename = UUID.randomUUID().toString().replace("-", "") + "." + suffix;
        String targetFilePath = uploadDirPath + pathSeparator + filename;

        File targetFile = new File(targetFilePath);
        file.transferTo(targetFile);
        return targetFile.getAbsolutePath();
    }

    /**
     * 删除文件
     * @param filePath 文件路径
     * @return 删除结果
     */
    public static Boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}
