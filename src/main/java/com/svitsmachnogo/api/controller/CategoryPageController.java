package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.CategoryPage;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.dto.*;
import com.svitsmachnogo.api.exceptions.IncorrectSortingCriteriaException;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
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

    private final ProductListForView productListForView;

    private final FilteringBlockService filteringBlockService;

    private final CategoryPage categoryPage;

    @PostMapping("/data/{categoryId}")
    public CategoryPageDTO getProductsForCategoryPageWithFilterBlock(
                                    @PathVariable(name = "categoryId") String categoryId,
                                    @RequestParam(required = false, defaultValue = "0")
                                    @Parameter(description = "Page number to display.By default 0 ") int page,
                                    @RequestParam(required = false, defaultValue = "20")
                                    @Parameter(description = "Number of products per page. By default 20") int size,
                                    @RequestParam(required = false, defaultValue = "by_popularity")
                                    @Parameter(description = "Criteria of sorting") String sort,
                                    @RequestBody CategoryPageRequestDTO requestBody
                                    ) throws IncorrectSortingCriteriaException {
        CategoryPageDTO pageAndFilterBlock = new CategoryPageDTO();
        PageDataDTO<ProductDTO> products = new PageDataDTO();

        categoryPage.generatePage(categoryId, requestBody.getCheckboxes(),requestBody.getPriceFilter(),
                page, size , sort);

        products.setData(ProductDTO.getList(categoryPage.getPageOfProducts().getContent()));
        products.setPageCount(categoryPage.getPageOfProducts().getTotalPages());

        pageAndFilterBlock.setPageOfProducts(products);
        pageAndFilterBlock
                .setBlockOfCriteria(BlockOfCriteriaDTO.blockOfCriteriaDTOList(filteringBlockService.getBlocksOfCriteria()));
        return pageAndFilterBlock;
    }

}
