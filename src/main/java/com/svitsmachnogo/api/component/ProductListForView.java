package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.dao.abstractional.ProductPaginationDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.service.abstractional.ProductPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductListForView {

     private final ProductDAO productDAO;

     private final ProductPaginationService productPaginationService;

     private List<Product> productList = new ArrayList<>();

     public ProductListForView(ProductDAO productDAO, ProductPaginationService productPaginationService) {
          this.productDAO = productDAO;
          this.productPaginationService = productPaginationService;
     }

     public void buildByCategoryId(String categoryId){
          Set<Product> products = productDAO.findAllByCategoryId(categoryId);
          productList = new ArrayList<>(products);
     }

     private List<Integer> getIdList(){
         return productList
                 .stream()
                 .map(Product::getId)
                 .collect(Collectors.toList());
     }

     private String buildInBasedIdList(List<Integer> idList){
          StringBuilder builder = new StringBuilder();
          idList.forEach(i -> builder.append(i).append(","));
          builder.deleteCharAt(builder.length()-1);
          return builder.toString();
     }

     public List<Product> getPage(PageRequest pageRequest){
           return productPaginationService.getProducts(getIdList() , pageRequest);
     }

     public List<Product> getProductList() {
          return productList;
     }

     public void setProductList(List<Product> productList) {
          this.productList = productList;
     }
}
