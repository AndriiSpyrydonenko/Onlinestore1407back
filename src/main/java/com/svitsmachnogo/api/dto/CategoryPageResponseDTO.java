package com.svitsmachnogo.api.dto;


import lombok.Data;

import java.util.List;

@Data
public class CategoryPageResponseDTO {

    private List<BlockOfCriteriaDTO> blockOfCriteria;

    private PageDataDTO<ProductDTO> pageOfProducts;



}
