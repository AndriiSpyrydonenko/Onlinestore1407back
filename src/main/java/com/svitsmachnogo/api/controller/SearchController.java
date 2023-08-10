package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.GiftSetServiceImpl;
import com.svitsmachnogo.api.service.abstractional.CategoryService;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GiftSetServiceImpl giftSetService;

    @Autowired
    ProductService productService;

    @GetMapping("/category/{id}")
    public List<ProductDTO> allProductByCategory(@PathVariable String id){
        List<Product> products = categoryService.getByIdAndReturnAllProducts(id);
        return ProductDTO.getList(products);
    }

    @GetMapping("/{partName}")
    public List<ProductDTO> allProductsByCoincidence(@PathVariable String partName ){
        List<Product> products = productService.getByPartName(partName);
        return ProductDTO.getList(products);
    }

}
