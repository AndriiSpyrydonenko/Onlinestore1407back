package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.exceptions.IncorrectSortingCriteriaException;
import com.svitsmachnogo.api.service.abstractional.ProductPaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


     public Page<Product> getPage(PageRequest pageRequest){
           return productPaginationService.getProducts(getIdList() , pageRequest);
     }

     public Sort buildSort(String criteria) throws IncorrectSortingCriteriaException {
          Objects.requireNonNull(criteria , "The sorting criteria cannot be null!");
          Sort sort = switch (criteria) {
               case "by_popularity" -> Sort.by("numberOfOrders").descending();
               case "by_rating" -> Sort.by("rating").descending();
               case "by_increasing_price" -> Sort.by("minPrice").ascending();
               case "by_reduction_price" -> Sort.by("minPrice").descending();
               case "new_ones_first" -> Sort.by("createDate").descending();
               case "old_ones_first" -> Sort.by("createDate").ascending();
               case "promotional_firsts" -> Sort.by("discountPercent").descending();
               default -> throw new IncorrectSortingCriteriaException( criteria +" is not in the list of valid values." +
                       " Valid values: by_popularity, by_rating, by_increasing_price, by_reduction_price, new_ones_first," +
                       " old_ones_first, promotional_firsts");
          };

          return sort;
     }

     public List<Product> getProductList() {
          return productList;
     }

     public void setProductList(List<Product> productList) {
          this.productList = productList;
     }
}
