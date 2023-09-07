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
@RequestMapping("/api/filter")
public class ProductsFilterController {

    @Autowired
    private FilteringBlockService filteringBlockService;

    @Autowired
    private PriceFilter priceFilter;


    @PostMapping("/checkbox")
    public void subcategoryBlockByCheckbox(
            @RequestBody CheckboxForSubcategory checkbox) {

        filteringBlockService.refreshStateCategoryPageByCheckBox(checkbox);
    }

    @GetMapping("/subcategory_block")
    public List<BlockOfCriteriaDTO> getCurrentFilterBlock() {
        List<BlockOfCriteria> block = filteringBlockService.getBlocksOfCriteria();
        return BlockOfCriteriaDTO.blockOfCriteriaDTOList(block);
    }

    @GetMapping("/get_price_filter")
    public PriceFilter getPriceFilter() {
        return priceFilter;
    }

    @PostMapping("/put_price_filter")
    public void refreshPriceFilterState(@RequestBody PriceFilter priceFilter) {
        filteringBlockService.refreshPriceFilter(priceFilter);
        filteringBlockService.refreshStateCategoryPageByCheckBox(createFakeCheckbox(priceFilter));
    }

    private CheckboxForSubcategory createFakeCheckbox(PriceFilter priceFilter){
        CheckboxForSubcategory checkbox = new CheckboxForSubcategory();
        checkbox.setActive(false);
        checkbox.setCategoryId(priceFilter.getCategoryId());
        return checkbox;
    }

}

