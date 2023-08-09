package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getByPriorityForMainPage();
    List<Product> getByDiscountPercentForMainPage();

}
