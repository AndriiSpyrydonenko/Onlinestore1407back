package com.svitsmachnogo.api.dto.product;

import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import com.svitsmachnogo.api.dto.subcategory.SubcategorySimpleDto;
import lombok.Data;

import java.net.URL;
import java.util.List;

@Data
public class ProductAdditionDto {

    private String categoryId;

    private int article;

    private String name;

    private String description;

    private String bigDescription;

    private String nutritionalValue;

    private String usage;

    private String countryProducer;

    private int discountPercent = 0;

    private int totalQuantity ;

    private String unit;

    private List<SubcategorySimpleDto> subcategories;

    private List<PackagingDto> packagings;

    private List<URL> pictures;

}
