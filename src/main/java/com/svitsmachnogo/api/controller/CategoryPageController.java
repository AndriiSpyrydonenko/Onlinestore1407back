package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.dto.category_page.CategoryPageRequestDTO;
import com.svitsmachnogo.api.dto.category_page.CategoryPageResponseDTO;
import com.svitsmachnogo.api.exceptions.IncorrectSortingCriteriaException;
import com.svitsmachnogo.api.service.CategoryPageService;
import com.svitsmachnogo.api.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Endpoints for category page")
@RequestMapping("/api/category_page")
public class CategoryPageController {

    private final ProductService productService;

    private final CategoryPageService categoryPageService;

    @GetMapping("/price_filter/{categoryId}")
    @Operation(summary = "returns a min and max price for the specify category")
    public PriceFilter getPriceFilterByCategoryId(@PathVariable String categoryId) {
        return productService.getDefaultPriceFilterByCategoryId(categoryId);
    }


    @PostMapping("/data/{categoryId}")
    @Operation(summary = "returns a list of subcategory and product page with amount of pages ")
    public CategoryPageResponseDTO getProductsForCategoryPageWithFilterBlock(
            @PathVariable(name = "categoryId") String categoryId,
            @RequestParam(required = false, defaultValue = "0")
            @Parameter(description = "Page number to display.By default 0 ") int page,
            @RequestParam(required = false, defaultValue = "20")
            @Parameter(description = "Number of products per page. By default 20") int size,
            @RequestParam(required = false, defaultValue = "by_popularity")
            @Parameter(description = "Criteria of sorting") String sort,
            @RequestBody(required = false) CategoryPageRequestDTO requestBody
    ) throws IncorrectSortingCriteriaException {

        return categoryPageService.getCategoryPage(categoryId, page, size, sort, requestBody);
    }

}
