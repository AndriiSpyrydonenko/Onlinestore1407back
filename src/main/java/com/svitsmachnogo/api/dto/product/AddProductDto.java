package com.svitsmachnogo.api.dto.product;

import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import com.svitsmachnogo.api.dto.subcategory.AddSubcategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class AddProductDto {

    private String categoryId;

    private int article;

    private String name;

    private String description;

    private String bigDescription;

    private String nutritionalValue;

    private String usage;

    private String countryProducer;

    private boolean exist = true;

    private int discountPercent = 0;

    private int totalQuantity ;

    private String unit;

    private List<AddSubcategoryDto> subcategories;

    private List<PackagingDto> packagings;

    private List<String> picturesURL;

}
