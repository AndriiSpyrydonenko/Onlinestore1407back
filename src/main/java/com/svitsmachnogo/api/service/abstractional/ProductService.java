package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.product.AddProductDto;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> getByPriorityForMainPage();

    List<Product> getByDiscountPercentForMainPage();

    List<Product> getByPartName(String partName);

    Product getProductById(int id);

    Set<Product> getAllByCategoryId(String categoryId);

    PriceFilter getDefaultPriceFilterByCategoryId(String categoryId);

    void addProduct(AddProductDto product);

}
