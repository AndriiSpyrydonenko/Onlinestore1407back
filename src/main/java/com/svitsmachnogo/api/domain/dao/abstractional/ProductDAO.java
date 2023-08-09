package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> findByPriorityForMainPage();

    List<Product> findForDiscountBlock();

    List<Product> findAllByRatingWithLimit(int limit);

    Product findById(int id);

}
