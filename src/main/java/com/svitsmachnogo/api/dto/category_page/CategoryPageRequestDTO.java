package com.svitsmachnogo.api.dto.category_page;

import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;
import lombok.Data;

import java.util.List;

@Data
public class CategoryPageRequestDTO {

     private List<CheckboxForSubcategory> checkboxes;

     private PriceFilter priceFilter;

}
