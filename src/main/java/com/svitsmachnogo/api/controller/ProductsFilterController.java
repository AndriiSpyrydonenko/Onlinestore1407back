package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.embedded_entity.BlockOfCriteria;
import com.svitsmachnogo.api.dto.BlockOfCriteriaDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/filter")
public class ProductsFilterController {

    @Autowired
    SubcategoryService subcategoryService;

    @GetMapping("/subcategory_list/{category_id}")
    public List<BlockOfCriteriaDTO> getSubcategoryForCategoryPage(
            @PathVariable(name = "category_id") String categoryId){
        List<BlockOfCriteria> block = subcategoryService.buildBlockForCategoryPage(categoryId);
        return BlockOfCriteriaDTO.blockOfCriteriaDTOList(block);
    }

    @GetMapping("/subcategory/{category_id}/{isActive}")
    public List<ProductDTO> getProductBySubcategory(@PathVariable(name = "category_id") String categoryId,
                                                    @PathVariable(name = "isActive") boolean isActive){
        Set<Product> products = subcategoryService.refreshFilterState(categoryId , isActive);
        return null;//todo: make method body
    }

}

