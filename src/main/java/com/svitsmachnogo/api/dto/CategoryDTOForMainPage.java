package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTOForMainPage {

    private String id;

    private String name;

    private String fillPictureUrl;

    private String hoverPictureUrl;

    private CategoryDTOForMainPage() {
    }

    public CategoryDTOForMainPage(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.fillPictureUrl = category.getFillPictureUrl();
        this.hoverPictureUrl = category.getHoverPictureUrl();
    }

    public static List<CategoryDTOForMainPage> getList(List<Category> categories){
        List<CategoryDTOForMainPage> categoryList = new ArrayList<>();
        for (Category category : categories) {
            categoryList.add(new CategoryDTOForMainPage(category));
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
