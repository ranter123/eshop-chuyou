package com.chuyou.eshop.eshop.commodity.controller;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailPictureDTO;
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
 * @Description: 商品详情图片管理controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 19:51
 */
@RestController
@RequestMapping("/commodity/goods/detail/picture")
public class GoodsDetailPictureController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsDetailPictureController.class);

    /**
     * 商品详情图片管理service组件
     */
    @Autowired
    private GoodsDetailPictureService goodsDetailPictureService;

    /**
     * 批量上传图片
     * @param goodsDetailId 商品详情id
     * @param pictures 商品详情图片
     * @return 商品详情图片id
     */
    @PostMapping("/{goodsDetailId}")
    public List<Long> bachUploadPicture(@PathVariable("goodsDetailId") Long goodsDetailId, MultipartFile[] pictures) {
        try {
            return goodsDetailPictureService.batchUploadPicture(goodsDetailId, pictures);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询商品图片
     * @param id 商品图片id
     * @param request http请求
     * @param response http响应
     */
    @GetMapping("/show/{id}")
    public void getById(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        try {
            GoodsDetailPictureDTO picture = goodsDetailPictureService.getById(id);
            File file = new File(picture.getPicturePath());
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
     * 根据商品id删除图片
     * @param goodsDetailId 商品详情id
     * @return
     */
    @DeleteMapping("/{goodsDetailId}")
    public Boolean batchRemoveByGoodsId(@PathVariable("goodsDetailId") Long goodsDetailId) {
        try {
            goodsDetailPictureService.batchRemoveByGoodsDetailId(goodsDetailId);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
