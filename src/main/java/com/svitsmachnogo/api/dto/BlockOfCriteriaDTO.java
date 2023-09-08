package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.component.BlockOfCriteria;

import java.util.List;
import java.util.stream.Collectors;

public class BlockOfCriteriaDTO {
    private String title;

    List<SubcategoryDTO> subcategories;

    private BlockOfCriteriaDTO() {
    }

    public static BlockOfCriteriaDTO create(BlockOfCriteria block) {
        BlockOfCriteriaDTO list = new BlockOfCriteriaDTO();
        list.title = block.getTitle();
        list.subcategories = SubcategoryDTO.subcategoryDTOS(block.getSubcategories());
        return list;
    }

    public static List<BlockOfCriteriaDTO> blockOfCriteriaDTOList(List<BlockOfCriteria> block){
        return block
                .stream()
                .map(BlockOfCriteriaDTO::create)
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubcategoryDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }
}
