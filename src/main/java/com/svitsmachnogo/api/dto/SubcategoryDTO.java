package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SubcategoryDTO {

    private String id;

    private String name;

    private int productCount;

    private String categoryId;

    private boolean clickable = true;

    private Set<Integer> productsId = new TreeSet<>();

    private SubcategoryDTO() {
    }

    public static SubcategoryDTO createWithAllFields(Subcategory subcategory) {
        SubcategoryDTO dao = new SubcategoryDTO();

        dao.id = subcategory.getId();
        dao.name = subcategory.getName();
        dao.productCount = subcategory.getProductCount();
        dao.categoryId = subcategory.getCategory().getId();
        dao.clickable = subcategory.isClickable();
        dao.productsId = getProductsId(subcategory);
        return dao;
    }

    private static Set<Integer> getProductsId(Subcategory subcategory) {
        return subcategory.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
    }

    public static List<SubcategoryDTO> subcategoryDTOS(List<Subcategory> subcategories){
        List<SubcategoryDTO> dtos = new ArrayList<>();
        for(Subcategory subcategory : subcategories){
            dtos.add(SubcategoryDTO.createWithAllFields(subcategory));
        }
        return dtos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public Set<Integer> getProductsId() {
        return productsId;
    }

    public void setProductsId(Set<Integer> productsId) {
        this.productsId = productsId;
    }
}
