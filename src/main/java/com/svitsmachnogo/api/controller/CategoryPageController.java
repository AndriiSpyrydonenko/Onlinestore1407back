package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.FilteringBlockServiceImpl;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category_page")
public class CategoryPageController {

    @Autowired
    private ProductListForView products;

    @Autowired
    private FilteringBlockService filteringBlockService;


    @GetMapping("/products")
    public List<ProductDTO> getProductsByCategory(){
        List<Product> productList = products.getProductList();
        return ProductDTO.getList(productList);
    }

    public ProductListForView getProducts() {
        return products;
    }

    public void setProducts(ProductListForView products) {
        this.products = products;
    }

    public FilteringBlockService getFilteringBlockService() {
        return filteringBlockService;
    }

    public void setFilteringBlockService(FilteringBlockServiceImpl filteringBlockService) {
        this.filteringBlockService = filteringBlockService;
    }
}
