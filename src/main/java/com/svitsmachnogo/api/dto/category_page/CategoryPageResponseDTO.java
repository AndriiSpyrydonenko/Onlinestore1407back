package com.svitsmachnogo.api.dto.category_page;


import com.svitsmachnogo.api.dto.BlockOfCriteriaDTO;
import com.svitsmachnogo.api.dto.PageDataDTO;
import com.svitsmachnogo.api.dto.product.ProductDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryPageResponseDTO {

    private List<BlockOfCriteriaDTO> blockOfCriteria;

    private PageDataDTO<ProductDTO> pageOfProducts;



}
