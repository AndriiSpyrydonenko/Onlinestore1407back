package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.exceptions.IncorrectSortingCriteriaException;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CategoryPage {

    private final ProductListForView products;

    private final FilteringBlockService filteringBlockService;

    private final PriceFilter priceFilter;

    private Page<Product> pageOfProducts;

    private List<BlockOfCriteria> blockOfCriteria;

    public  void generatePage (String categoryId , List<CheckboxForSubcategory> checkboxes,
                               PriceFilter priceFilter,
                               int page, int size,
                               String criteriaOfSorting) throws IncorrectSortingCriteriaException {

        if((checkboxes== null || checkboxes.size() == 0 ) && (priceFilter == null || priceFilter.getMinPrice() == null) ){
            filteringBlockService.refreshStateCategoryPageByCategoryId(categoryId);
        }else {
            filteringBlockService.refreshStateCategoryPageByCheckBox(categoryId , checkboxes , priceFilter);
        }
        pageOfProducts = products.getPage(PageRequest.of(page, size, products.buildSort(criteriaOfSorting)));
        blockOfCriteria = filteringBlockService.getBlocksOfCriteria();
    }


}
