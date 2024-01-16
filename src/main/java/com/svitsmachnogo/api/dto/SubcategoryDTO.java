package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Data
public class SubcategoryDTO {

    private String id;

    private String name;

    private int productCount;

    private String categoryId;

    private boolean clickable = true;

    private boolean active = false;

    private Set<Integer> productsId = new TreeSet<>();

    private SubcategoryDTO() {
    }

    public static SubcategoryDTO createWithAllFields(Subcategory subcategory) {
        SubcategoryDTO dto = new SubcategoryDTO();

        dto.id = subcategory.getId();
        dto.name = subcategory.getName();
        dto.productCount = subcategory.getProductCount();
        dto.categoryId = subcategory.getCategory().getId();
        dto.clickable = subcategory.isClickable();
        dto.active = subcategory.isActive();
        dto.productsId = getProductsId(subcategory);
        return dto;
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
}
