package com.chuyou.eshop.eshop.commodity.controller;

import com.chuyou.eshop.eshop.commodity.domain.GoodsPictureDTO;
import com.chuyou.eshop.eshop.commodity.service.GoodsPictureService;
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
 * @Description: 商品图片管理controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/3 17:22
 */
@RestController
@RequestMapping("/commodity/goods/picture")
public class GoodsPictureController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsPictureController.class);

    /**
     * 商品图片管理service组件
     */
    @Autowired
    private GoodsPictureService goodsPictureService;

    /**
     * 批量上次图片
     * @param goodsId 商品id
     * @param pictures 商品图片
     * @return 处理结果
     */
    @PostMapping("/{goodsId}")
    public Boolean batchService(@PathVariable("goodsId") Long goodsId, MultipartFile[] pictures) {
        try {
            goodsPictureService.batchSave(goodsId, pictures);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据商品id查询商品图片id
     * @param goodsId 商品id
     * @return 商品图片id
     */
    @GetMapping("/{goodsId}")
    public List<Long> listIdsByGoodsId(@PathVariable("goodsId") Long goodsId) {
        try {
            return goodsPictureService.listIdsByGoodsId(goodsId);
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
            GoodsPictureDTO picture = goodsPictureService.getById(id);
            File file = new File(picture.getPicturePath());
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            response.setContentType("image/jpg");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
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

    @DeleteMapping("/{goodsId}")
    public Boolean batchRemoveByGoodsId(@PathVariable("goodsId") Long goodsId) {
        try {
            goodsPictureService.batchRemoveByGoodsId(goodsId);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
