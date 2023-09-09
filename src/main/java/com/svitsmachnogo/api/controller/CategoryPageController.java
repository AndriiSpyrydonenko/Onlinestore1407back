package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.dto.PageDataDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Endpoints for category page")
@RequestMapping("/api/category_page")
public class CategoryPageController {

    private final ProductListForView products;

    private final FilteringBlockService filteringBlockService;

    public CategoryPageController(ProductListForView products, FilteringBlockService filteringBlockService) {
        this.products = products;
        this.filteringBlockService = filteringBlockService;
    }


    @Operation(summary = "The most important endpoint that generates the output state for this category. " +
            "It must be called first for a category page. Does not give an response.")
    @GetMapping("/build_new_page/{categoryId}")
    public void defaultSubcategoryBlock(
            @PathVariable(name = "categoryId") String categoryId) {

        filteringBlockService.refreshStateCategoryPageByCategoryId(categoryId);
    }

    @Operation(summary = "Return a paginated list of products",
            description = "Retrieve a paginated list of products with optional size and page parameters.")
    @GetMapping("/products")
    public PageDataDTO<ProductDTO> getProductsForCategoryPage(
            @RequestParam(required = false, defaultValue = "0")
            @Parameter(description = "Page number to display.By default 0 ") int page,
            @RequestParam(required = false, defaultValue = "20")
            @Parameter(description = "Number of products per page. By default 20") int size,
            @RequestParam(required = false, defaultValue = "id")
            @Parameter(description = "Criteria of sorting") String sort
    ) {
        PageDataDTO<ProductDTO> dataDTO = new PageDataDTO<>();
        var pageProduct = products.getPage(PageRequest.of(page, size , Sort.by(sort)));
        var productDTOList = ProductDTO.getList(pageProduct.getContent());
        dataDTO.setData(productDTOList);
        dataDTO.setPageCount(pageProduct.getTotalPages());
        return dataDTO;
    }

}
