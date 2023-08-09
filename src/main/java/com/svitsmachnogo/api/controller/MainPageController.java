package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.domain.entity.GiftSet;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.CategoryDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.abstractional.CategoryService;
import com.svitsmachnogo.api.service.GiftSetServiceImpl;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/main_page")
public class MainPageController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GiftSetServiceImpl giftSetService;

    @Autowired
    ProductService productService;

    @GetMapping("/categories")
    public List<CategoryDTO> categoriesForMainPage(){
        List<Category> categories = categoryService.findAllForMainPage();
        return CategoryDTO.getList(categories);
    }

    @GetMapping("/gift_set")
    public GiftSet setGiftSetForMainPage(){
        return giftSetService.getForMainPage();
    }

    @GetMapping("/products")
    public List<ProductDTO> setProductsToPriorityBlock(){
        List<Product> products = productService.getByPriorityForMainPage();
        return ProductDTO.getList(products);
    }

    @GetMapping("/discount_products")
    public List<ProductDTO> setDiscountBlock(){
        List<Product> products = productService.getByDiscountPercentForMainPage();
        return ProductDTO.getListForDiscountBlock(products);
    }
}
