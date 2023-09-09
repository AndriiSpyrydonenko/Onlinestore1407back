package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.ProductPaginationDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.service.abstractional.ProductPaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPaginationServiceImpl  implements ProductPaginationService {

    private final ProductPaginationDAO productPaginationDAO;


    public ProductPaginationServiceImpl(ProductPaginationDAO productPaginationDAO) {
        this.productPaginationDAO = productPaginationDAO;
    }

    public Page<Product> getProducts(List<Integer> idList, PageRequest pageRequest){
        return productPaginationDAO.getAll(idList , pageRequest);

    }
}
