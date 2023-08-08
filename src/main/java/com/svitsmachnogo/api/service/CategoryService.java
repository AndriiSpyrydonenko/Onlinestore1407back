package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllForMainPage();

}
