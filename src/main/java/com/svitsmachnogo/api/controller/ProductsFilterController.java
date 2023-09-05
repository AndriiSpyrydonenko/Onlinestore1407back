package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.BlockOfCriteriaDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.exceptions.WrongPriceFilterException;
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

    @Autowired
    private PriceFilter priceFilter;


    @GetMapping("/subcategory_block/{categoryId}")
    public List<BlockOfCriteriaDTO> defaultSubcategoryBlock(
            @PathVariable(name = "categoryId") String categoryId) throws WrongPriceFilterException {

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

    @GetMapping("/price_filter")
    public PriceFilter getPriceFilter(){
        return priceFilter;
    }

    @PostMapping("/price_filter")
    public void refreshPriceFilterState(@RequestBody PriceFilter priceFilter){
        filteringBlockService.refreshPriceFilter(priceFilter);
        CheckboxForSubcategory checkbox = new CheckboxForSubcategory();
        checkbox.setActive(false);
        filteringBlockService.refreshStateCategoryPageByCheckBox(checkbox);
    }

}

