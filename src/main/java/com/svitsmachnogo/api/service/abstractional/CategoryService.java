package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.domain.entity.Product;

import java.util.List;

public interface CategoryService {

    List<Category> findAllForMainPage();

    List<Product>  getByIdAndReturnAllProducts(String id);

    Category findById(String categoryId);

}
