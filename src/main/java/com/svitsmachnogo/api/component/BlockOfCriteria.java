package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.entity.Subcategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlockOfCriteria {

    private String title;

    List<Subcategory> subcategories;

    private BlockOfCriteria() {
    }

    public static BlockOfCriteria create(String title , List<Subcategory> subcategories){
        BlockOfCriteria block = new BlockOfCriteria();
        block.title = title;
        block.subcategories = subcategories;
        return block;
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
