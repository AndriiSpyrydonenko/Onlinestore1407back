package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "An object that stores information about the Category for further transportation")

public class CategoryDTO {

    @Schema(description = "Id is the name of the category in English")
    @NotBlank
    private String id;

    @Schema(description = "Category name in Ukrainian")
    @NotBlank
    private String name;


    @Schema(description = "contains a link to the image of the category in the fill state")
    private String fillPictureUrl;

    @Schema(description = "contains a link to the image of the category in the hover state")
    private String hoverPictureUrl;

    private CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.fillPictureUrl = category.getFillPictureUrl();
        this.hoverPictureUrl = category.getHoverPictureUrl();
    }

    public static List<CategoryDTO> getList(List<Category> categories) {
        List<CategoryDTO> categoryList = new ArrayList<>();
        for (Category category : categories) {
            categoryList.add(new CategoryDTO(category));
        }
        return categoryList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFillPictureUrl() {
        return fillPictureUrl;
    }

    public void setFillPictureUrl(String fillPictureUrl) {
        this.fillPictureUrl = fillPictureUrl;
    }

    public String getHoverPictureUrl() {
        return hoverPictureUrl;
    }

    public void setHoverPictureUrl(String hoverPictureUrl) {
        this.hoverPictureUrl = hoverPictureUrl;
    }
}
