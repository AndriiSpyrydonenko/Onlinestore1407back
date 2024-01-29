package com.svitsmachnogo.api.dto.subcategory;

import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.dto.DtoFactory;

public class SubcategorySimpleDtoFactory implements DtoFactory<Subcategory> {
    @Override
    public SubcategorySimpleDto of(Subcategory targetEntity) {

        SubcategorySimpleDto dto = new SubcategorySimpleDto();

        dto.setSubcategoryId(targetEntity.getId());
        dto.setSubcategoryName(targetEntity.getName());
        dto.setSubcategoryTitle(targetEntity.getTitle());

        return dto;
    }
}
