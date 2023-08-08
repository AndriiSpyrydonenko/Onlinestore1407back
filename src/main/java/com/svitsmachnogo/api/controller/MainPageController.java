package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.dto.CategoryDTOForMainPage;
import com.svitsmachnogo.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main_page")
public class MainPageController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTOForMainPage> categoriesForMainPage(){
        List<Category> categories = categoryService.findAllForMainPage();
        return CategoryDTOForMainPage.getList(categories);
    }

}
