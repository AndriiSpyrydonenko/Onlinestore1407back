package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ProductListForView {

     @Autowired
     private ProductDAO productDAO;
     private List<Product> productList = new ArrayList<>();

     public void buildByCategoryId(String categoryId){
          Set<Product> products = productDAO.findAllByCategoryId(categoryId);
          productList = new ArrayList<>(products);
     }

     public ProductListForView() {
     }

     public List<Product> getProductList() {
          return productList;
     }

     public void setProductList(List<Product> productList) {
          this.productList = productList;
     }
}
