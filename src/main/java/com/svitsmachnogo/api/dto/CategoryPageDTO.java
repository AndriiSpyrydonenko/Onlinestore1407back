package com.svitsmachnogo.api.dto;


import com.svitsmachnogo.api.component.PriceFilter;
import lombok.Data;

import java.util.List;

@Data
public class CategoryPageDTO {

    private List<BlockOfCriteriaDTO> blockOfCriteria;

    private PageDataDTO<ProductDTO> pageOfProducts;



}
