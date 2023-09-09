package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.PageDataDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.FilteringBlockServiceImpl;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Endpoints for category page")
@RequestMapping("/api/category_page")
public class CategoryPageController {

    @Autowired
    private ProductListForView products;

    @Autowired
    private FilteringBlockService filteringBlockService;


    @Operation(summary = "The most important endpoint that generates the output state for this category. " +
            "It must be called first for a category page. Does not give an response.")
    @GetMapping("/build_new_page/{categoryId}")
    public void defaultSubcategoryBlock(
            @PathVariable(name = "categoryId") String categoryId) {

        filteringBlockService.refreshStateCategoryPageByCategoryId(categoryId);
    }

    @Operation(summary = "Returns actual product list according current category and filter criteria")
    @GetMapping("/products")
    public PageDataDTO<ProductDTO> getProductsForCategoryPage(
            @RequestParam(required = false , defaultValue = "0") int page,
            @RequestParam(required = false , defaultValue = "2") int size
    ){
        PageDataDTO<ProductDTO> dataDTO = new PageDataDTO<>();
        var pageProduct = products.getPage(PageRequest.of(page, size));
        var productDTOList = ProductDTO.getList(pageProduct.getContent());
        dataDTO.setData(productDTOList);
        dataDTO.setTotal(pageProduct.getTotalElements());
        return dataDTO;
    }

}
