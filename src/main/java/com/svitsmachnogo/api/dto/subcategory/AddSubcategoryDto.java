package com.svitsmachnogo.api.dto.subcategory;

import com.svitsmachnogo.api.domain.entity.Subcategory;
import lombok.Data;

@Data
public class AddSubcategoryDto {

    private String subcategoryId;

    private String subcategoryName;

    private String subcategoryTitle;
}


