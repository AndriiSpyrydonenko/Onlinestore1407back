package com.svitsmachnogo.api.dto.subcategory;

import com.svitsmachnogo.api.dto.AbstractDto;
import lombok.Data;

@Data
public class SubcategorySimpleDto implements AbstractDto {

    private String subcategoryId;

    private String subcategoryName;

    private String subcategoryTitle;
}


