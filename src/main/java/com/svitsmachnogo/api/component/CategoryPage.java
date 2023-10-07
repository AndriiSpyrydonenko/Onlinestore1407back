package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.*;
import com.svitsmachnogo.api.exceptions.IncorrectSortingCriteriaException;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryPage {

    private final ProductListForView products;

    private final FilteringBlockService filteringBlockService;

    private Page<Product> pageOfProducts;

    public CategoryPageDTO getCategoryPage(String categoryId, int page, int size, String sort, CategoryPageRequestDTO requestBody) throws IncorrectSortingCriteriaException {
        CategoryPageDTO pageAndFilterBlock = new CategoryPageDTO();
        PageDataDTO<ProductDTO> products = new PageDataDTO<>();

        if(requestBody != null) {
            generatePage(categoryId, requestBody.getCheckboxes(), requestBody.getPriceFilter(),
                    page, size, sort);
        }else {
            generatePage(categoryId, null, null,
                    page, size, sort);
        }
        products.setData(ProductDTO.getList(pageOfProducts.getContent()));
        products.setPageCount(pageOfProducts.getTotalPages());

        pageAndFilterBlock.setPageOfProducts(products);
        pageAndFilterBlock
                .setBlockOfCriteria(BlockOfCriteriaDTO
                        .blockOfCriteriaDTOList(filteringBlockService.getBlocksOfCriteria()));
        filteringBlockService.clearState();
        return pageAndFilterBlock;
    }

    private void generatePage (String categoryId , List<CheckboxForSubcategory> checkboxes,
                               PriceFilter priceFilter,
                               int page, int size,
                               String criteriaOfSorting) throws IncorrectSortingCriteriaException {

        if((checkboxes== null || checkboxes.size() == 0 ) && (priceFilter == null || priceFilter.getMinPrice() == null) ){
            filteringBlockService.buildDefaultFilteringBlockByCategoryId(categoryId);
        }else {
            filteringBlockService.buildFilteringBlockByFilterAspects(categoryId , checkboxes , priceFilter);
        }
        pageOfProducts = products.getPage(PageRequest.of(page, size, products.buildSort(criteriaOfSorting)));
    }


}
