package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.*;
import com.svitsmachnogo.api.exceptions.IncorrectSortingCriteriaException;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryPageService {

    private final ProductListForView products;

    private final FilteringBlockService filteringBlockService;

    private Page<Product> pageOfProducts;

    public CategoryPageResponseDTO getCategoryPage(String categoryId, int page, int size, String sort,
                                                   CategoryPageRequestDTO requestBody) throws IncorrectSortingCriteriaException {

        PageDataDTO<ProductDTO> products = new PageDataDTO<>();
        CategoryPageResponseDTO pageAndFilterBlock = new CategoryPageResponseDTO();


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
