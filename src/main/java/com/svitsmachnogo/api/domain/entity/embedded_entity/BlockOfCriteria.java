package com.svitsmachnogo.api.domain.entity.embedded_entity;

import com.svitsmachnogo.api.domain.entity.Subcategory;

import java.util.List;

public class BlockOfCriteria {

    private String title;

    List<Subcategory> subcategories;

    private BlockOfCriteria() {
    }

    public static BlockOfCriteria create(String title , List<Subcategory> subcategories){
        BlockOfCriteria list = new BlockOfCriteria();
        list.title = title;
        list.subcategories = subcategories;
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
