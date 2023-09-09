package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductPaginationService {

    Page<Product> getProducts(List<Integer> idList, PageRequest pageRequest);

}
