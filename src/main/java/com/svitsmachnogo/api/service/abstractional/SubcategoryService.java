package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.component.BlockOfCriteria;

import java.util.List;
import java.util.Set;

public interface SubcategoryService {

    List<Subcategory> getAllSubcategoryByCategoryId(String categoryId);

    Set<Product> getProductsBySubcategory(String subcategoryId);

}
