package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SubcategoryService {

    List<Subcategory> getAllSubcategoryByCategoryId(String categoryId);

    Set<Product> getProductsBySubcategory(String subcategoryId);

    Optional<Subcategory> findById(String id);

    void save(Subcategory subcategory);

}
