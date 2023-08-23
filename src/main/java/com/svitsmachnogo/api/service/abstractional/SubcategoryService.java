package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.domain.entity.embedded_entity.BlockOfCriteria;

import java.util.List;
import java.util.Set;

public interface SubcategoryService {

    List<Subcategory> getAllForCategoryPage(String categoryId);

    List<BlockOfCriteria> buildBlockForCategoryPage(String categoryId);

    Set<Product> getProductsBySubcategory(String subcategoryId);

    Set<Product> productFilteringBySubcategory (List<Subcategory> subcategories);

    Set<Product> refreshFilterState(String subcategoryId , boolean isActive);

}
