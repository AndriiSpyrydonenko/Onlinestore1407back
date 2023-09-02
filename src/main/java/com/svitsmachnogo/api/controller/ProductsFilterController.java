package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.BlockOfCriteriaDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/filter")
public class ProductsFilterController {

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private ProductListForView products;

    @Autowired
    private FilteringBlockService filteringBlockService;


    @GetMapping("/subcategory_block/{categoryId}")
    public List<BlockOfCriteriaDTO> defaultSubcategoryBlock(
            @PathVariable(name = "categoryId") String categoryId){

       filteringBlockService.refreshStateCategoryPageByCategoryId(categoryId);
        List<BlockOfCriteria> defaultBlock = filteringBlockService.getBlocksOfCriteria();
        return BlockOfCriteriaDTO.blockOfCriteriaDTOList(defaultBlock);
    }

    @PostMapping("/subcategory_block")
    public List<BlockOfCriteriaDTO> subcategoryBlockByCheckbox(
            @RequestBody CheckboxForSubcategory checkbox){
        System.out.println(checkbox);

        filteringBlockService.refreshStateCategoryPageByCheckBox(checkbox);
        List<BlockOfCriteria> block = filteringBlockService.getBlocksOfCriteria();
        return BlockOfCriteriaDTO.blockOfCriteriaDTOList(block);
    }

    @GetMapping("/subcategory/products")
    public List<ProductDTO> getProductsByCheckboxes() {
        List<Product> productList = products.getProductList();
        return ProductDTO.getList(productList);
    }

    public FilteringBlockService getFilteringBlockService() {
        return filteringBlockService;
    }

    public void setFilteringBlockService(FilteringBlockService filteringBlockService) {
        this.filteringBlockService = filteringBlockService;
    }

    public SubcategoryService getSubcategoryService() {
        return subcategoryService;
    }

    public void setSubcategoryService(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    public ProductListForView getProducts() {
        return products;
    }

    public void setProducts(ProductListForView products) {
        this.products = products;
    }
}

