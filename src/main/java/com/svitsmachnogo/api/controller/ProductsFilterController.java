package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.dto.BlockOfCriteriaDTO;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Endpoints for block of filters")
@RequestMapping("/api/filter")
public class ProductsFilterController {

    private final FilteringBlockService filteringBlockService;

    private final PriceFilter priceFilter;

    public ProductsFilterController(FilteringBlockService filteringBlockService, PriceFilter priceFilter) {
        this.filteringBlockService = filteringBlockService;
        this.priceFilter = priceFilter;
    }


    @Operation(summary = "Accepts some checkbox and updates the page state according to the list of checkboxes." +
            " Does not given a response. ")
    @PostMapping("/checkbox")
    public void subcategoryBlockByCheckbox(
            @RequestBody CheckboxForSubcategory checkbox) {

        filteringBlockService.refreshStateCategoryPageByCheckBox(checkbox);
    }

    @Operation(summary ="Returns the actual block of criteria according to the checkbox list and the price filter ")
    @GetMapping("/subcategory_block")
    public List<BlockOfCriteriaDTO> getCurrentFilterBlock() {
        List<BlockOfCriteria> block = filteringBlockService.getBlocksOfCriteria();
        return BlockOfCriteriaDTO.blockOfCriteriaDTOList(block);
    }

    @Operation(summary = "Returns the actual price filter. " +
            "Returns the minimum and maximum product prices from the actual product list by default ")
    @GetMapping("/get_price_filter")
    public PriceFilter getPriceFilter() {
        return priceFilter;
    }

    @Operation(summary = "Accepts the some price filter and updates " +
            "the page state according to the given price filter. Doesn't give a response.")
    @PostMapping("/put_price_filter")
    public void refreshPriceFilterState(@RequestBody PriceFilter priceFilter) {
        filteringBlockService.refreshPriceFilter(priceFilter);
        filteringBlockService.refreshStateCategoryPageByCheckBox(createFakeCheckbox(priceFilter));
    }

    private CheckboxForSubcategory createFakeCheckbox(PriceFilter priceFilter) {
        CheckboxForSubcategory checkbox = new CheckboxForSubcategory();
        checkbox.setActive(false);
        checkbox.setCategoryId(priceFilter.getCategoryId());
        return checkbox;
    }

}

