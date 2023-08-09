package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllForMainPage();

}
