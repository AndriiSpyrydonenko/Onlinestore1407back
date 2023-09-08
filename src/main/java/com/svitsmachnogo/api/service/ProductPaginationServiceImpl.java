package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.dao.abstractional.ProductPaginationDAO;
import com.svitsmachnogo.api.service.abstractional.ProductPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPaginationServiceImpl  implements ProductPaginationService {

    @Autowired
    private ProductPaginationDAO productPaginationDAO;

    @Autowired
    ProductListForView productListForView;

}
