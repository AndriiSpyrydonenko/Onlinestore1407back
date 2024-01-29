package com.svitsmachnogo.api.service.product;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.domain.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> getByPriorityForMainPage();

    List<Product> getByDiscountPercentForMainPage();

    List<Product> getByPartName(String partName);

    Product getProductById(int id);

    Set<Product> getAllByCategoryId(String categoryId);

    PriceFilter getDefaultPriceFilterByCategoryId(String categoryId);

}
