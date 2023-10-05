package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.domain.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductDAO {

    List<Product> findByPriorityForMainPage();

    List<Product> findForDiscountBlock();

    List<Product> findAllByRatingWithLimit(int limit);

    Product findById(int id);

    List<Product> findByPartName(String partName);

    Set<Product> findAllByCategoryId(String categoryId);

    PriceFilter findMinAndMaxPrice(String categoryId);
}
