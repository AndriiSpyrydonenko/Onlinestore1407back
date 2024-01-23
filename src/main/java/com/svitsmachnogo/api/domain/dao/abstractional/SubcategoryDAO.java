package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SubcategoryDAO {

    List<Subcategory> findAllByCategoryId(String categoryId);

    Optional<Subcategory> findById(String subcategoryId);
}
