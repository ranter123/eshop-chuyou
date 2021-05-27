package com.chuyou.eshop.eshop.commodity.controller;

import com.chuyou.eshop.eshop.commodity.domain.BrandDTO;
import com.chuyou.eshop.eshop.commodity.domain.BrandQuery;
import com.chuyou.eshop.eshop.commodity.domain.BrandVO;
import com.chuyou.eshop.eshop.commodity.service.BrandService;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: ranter
 * @Date: 2021/4/29 10:46 下午
 * @Description: 品牌管理controller组件
 */
@RestController
@RequestMapping("/commodity/brand")
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    /**
     * 品牌管理service组件
     */
    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌
     */
    @GetMapping("/")
    public List<BrandVO> listByPage(BrandQuery query) {
        try {
            return ObjectUtils.convertList(brandService.listByPage(query), BrandVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    @GetMapping("/{id}")
    public BrandVO getById(@PathVariable("id") Long id) {
        try {
            return brandService.getById(id).clone(BrandVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new BrandVO();
        }
    }

    /**
     * 新增品牌
     * @param brand 品牌
     * @param logoFile logo文件
     * @param authVoucherFile 认证文件
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean save(BrandVO brand, MultipartFile logoFile, MultipartFile authVoucherFile) {
        try {
            brandService.save(brand.clone(BrandDTO.class), logoFile, authVoucherFile);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新品牌
     * @param brand 品牌
     * @return 处理结果
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody BrandVO brand) {
        try {
            brandService.update(brand.clone(BrandDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新logo图片
     * @param id 文件id
     * @param logoFile 图片文件
     * @return 处理结果
     */
    @PostMapping("/logo/{id}")
    public Boolean updateLogoPicture(@PathVariable("id") Long id, MultipartFile logoFile) {
        try {
            brandService.updateLogoPicture(id, logoFile);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新logo图片
     * @param id 品牌ID
     * @param authVoucherFile 品牌认证文件
     * @return
     */
    @PostMapping("/authVoucher/{id}")
    public Boolean updateAuthVoucherPicture(@PathVariable("id") Long id, MultipartFile authVoucherFile) {
        try {
            brandService.updateAuthVoucherPicture(id, authVoucherFile);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除品牌
     * @param id 品牌ID
     * @return 处理结果
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            brandService.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 显示logo图片
     * @param id 品牌ID
     * @param request 请求
     * @param response 响应
     */
    @GetMapping("/logo/{id}")
    public void viewLogo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        try {
            BrandDTO brand = brandService.getById(id);
            File file = new File(brand.getLogoPath());
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
                } catch (IOException e) {
                    logger.error("error", e);
                }
            }
        }
    }

    /**
     * 显示品牌授权认证图片
     * @param id 品牌ID
     * @param request 请求
     * @param response 响应
     */
    @GetMapping("/logo/{id}")
    public void viewAuthVoucher(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        try {
            BrandDTO brand = brandService.getById(id);
            File file = new File(brand.getAuthVoucherPath());
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
                } catch (IOException e) {
                    logger.error("error", e);
                }
            }
        }
    }
}
