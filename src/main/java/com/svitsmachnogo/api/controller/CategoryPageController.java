package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.CategoryDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category_page")
public class CategoryPageController {

    @Autowired
    ProductService productService;


    @GetMapping("/product/{category_id}")
    public List<ProductDTO> getProductsForCategoryPage(@PathVariable(name = "category_id") String categoryId){
        Set<Product> products = productService.getAllByCategoryId(categoryId);
        return ProductDTO.getList(convertSetToList(products));
    }


    private static List<Product> convertSetToList(Set<Product> products) {
        return new ArrayList<>(products);
    }
}
