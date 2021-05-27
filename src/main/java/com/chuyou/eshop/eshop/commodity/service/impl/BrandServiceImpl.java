package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.constant.PathType;
import com.chuyou.eshop.eshop.commodity.dao.BrandDAO;
import com.chuyou.eshop.eshop.commodity.domain.BrandDO;
import com.chuyou.eshop.eshop.commodity.domain.BrandDTO;
import com.chuyou.eshop.eshop.commodity.domain.BrandQuery;
import com.chuyou.eshop.eshop.commodity.service.BrandService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.FileUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/29 10:48 下午
 * @Description: 品牌管理service组件
 */
@Service
public class BrandServiceImpl implements BrandService {

    /**
     * 品牌管理DAO组件
     */
    @Autowired
    private BrandDAO brandDAO;

    /**
     * logo图片的路径类型
     */
    @Value("${commodity.brand.image.upload.logo.path.type}")
    private String logoPathType;

    /**
     * logo图片的上传路径
     */
    @Value("${commodity.brand.image.upload.logo.path}")
    private String logoPath;

    /**
     * 品牌授权认证图片的路径类型
     */
    @Value("${commodity.brand.image.upload.authVoucher.path.type}")
    private String authVoucherPathType;
    /**
     * 品牌授权认证图片的上传路径
     */
    @Value("${commodity.brand.image.upload.authVoucher.path}")
    private String authVoucherPath;

    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌
     * @throws Exception
     */
    @Override
    public List<BrandDTO> listByPage(BrandQuery query) throws Exception {
        return ObjectUtils.convertList(brandDAO.listByPage(query), BrandDTO.class);
    }

    /**
     * 根据品牌id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    @Override
    public BrandDTO getById(Long id) throws Exception{
        return brandDAO.getById(id).clone(BrandDTO.class);
    }

    /**
     * 新增品牌
     * @param brand 品牌
     * @param logoFile logo文件
     * @param authVoucherFile 认证文件
     * @throws Exception
     */
    @Override
    public void save(BrandDTO brand, MultipartFile logoFile,
                     MultipartFile authVoucherFile) throws Exception{
        String logoPath = uploadLogoFile(logoFile);
        String authVoucherPath = uploadAuthVoucherFile(authVoucherFile);

        brand.setLogoPath(logoPath);
        brand.setAuthVoucherPath(authVoucherPath);
        brand.setGmtCreate(DateUtils.getCurrentTime());
        brand.setGmtModified(DateUtils.getCurrentTime());

        brandDAO.save(brand.clone(BrandDO.class));
    }

    /**
     * 编辑品牌
     * @param brand 品牌
     * @throws Exception
     */
    @Override
    public void update(BrandDTO brand) throws Exception {
        brand.setGmtModified(DateUtils.getCurrentTime());
        brandDAO.update(brand.clone(BrandDO.class));
    }

    /**
     * 更新品牌logo图片
     * @param id 图片ID
     * @param logoFile 图片文件
     */
    @Override
    public void updateLogoPicture(Long id, MultipartFile logoFile) throws Exception {
        BrandDO brand = brandDAO.getById(id);

        FileUtils.deleteFile(brand.getLogoPath());
        String logoPath = uploadLogoFile(logoFile);

        brand.setGmtModified(DateUtils.getCurrentTime());
        brand.setLogoPath(logoPath);
        brandDAO.updateLogoPath(brand);
    }

    /**
     * 更新品牌的授权书图片
     * @param id 品牌ID
     * @param authVoucherFile 品牌授权书图片
     */
    @Override
    public void updateAuthVoucherPicture(Long id, MultipartFile authVoucherFile) throws Exception{
        BrandDO brand = brandDAO.getById(id);

        FileUtils.deleteFile(brand.getAuthVoucherPath());
        String authVoucherPath = uploadAuthVoucherFile(authVoucherFile);

        brand.setGmtModified(DateUtils.getCurrentTime());
        brand.setAuthVoucherPath(authVoucherPath);
        brandDAO.updateAuthcherPath(brand);
    }

    /**
     * 删除品牌
     * @param id 品牌ID
     */
    @Override
    public void remove(Long id) {
        BrandDO brand = brandDAO.getById(id);
        FileUtils.deleteFile(brand.getLogoPath());
        FileUtils.deleteFile(brand.getAuthVoucherPath());
        brandDAO.remove(id);
    }

    /**
     * 上传品牌授权认证图片
     * @param authVoucherFile 授权认证文件
     * @return 授权认证文件绝对路径
     * @throws Exception
     */
    private String uploadAuthVoucherFile(MultipartFile authVoucherFile) throws Exception{
        if (authVoucherFile == null) {
            return null;
        }
        String uploadDirPath = null;
        if (PathType.RELATIVE.equals(authVoucherPathType)) {
            uploadDirPath = FileUtils.getPathByRelative(authVoucherPath);
        } else {
            uploadDirPath = authVoucherPath;
        }

        String uploadFilePath = FileUtils.uploadFile(authVoucherFile, uploadDirPath);
        return uploadDirPath;
    }

    /**
     * 上传logo文件
     * @param logoFile logo文件
     * @return logo文件路径
     */
    private String uploadLogoFile(MultipartFile logoFile) throws Exception {
        if (logoFile == null) {
            return null;
        }

        String uploadDirPath = null;
        if (PathType.RELATIVE.equals(logoPathType)) {
            uploadDirPath = FileUtils.getPathByRelative(logoPath);
        } else {
            uploadDirPath = logoPath;
        }

        String uploadFilePath = FileUtils.uploadFile(logoFile, uploadDirPath);

        return uploadFilePath;
    }
}
