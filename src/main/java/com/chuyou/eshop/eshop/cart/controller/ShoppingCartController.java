package com.chuyou.eshop.eshop.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ranter
 * @Date: 2021/4/29 9:29 下午
 * @Description: 购物车管理模块的controller组件
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    /**
     * 购物车管理模块的service组件
     */
    private ShoppingCartService shoppingCartService;

    @PostMapping("/item/add")
    public Boolean addShoppingCartItem(@RequestBody AddShoppingCartItemQuery query) {

    }

    @GetMapping("/{userAccountId}")
    public ShoppingCartVO getShoppingCartVO () {

    }
}
