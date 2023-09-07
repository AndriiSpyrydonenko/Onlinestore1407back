package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.FilteringBlockServiceImpl;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/category_page")
public class CategoryPageController {

    @Autowired
    private ProductListForView products;

    @Autowired
    private FilteringBlockService filteringBlockService;


    @GetMapping("/build_new_page/{categoryId}")
    public void defaultSubcategoryBlock(
            @PathVariable(name = "categoryId") String categoryId) {

        filteringBlockService.refreshStateCategoryPageByCategoryId(categoryId);
    }

    @GetMapping("/products")
    public List<ProductDTO> getProductsForCategoryPage(){
        List<Product> productList = products.getProductList();
        return ProductDTO.getList(productList);
    }

}
