package com.svitsmachnogo.api.domain.dao;

import com.svitsmachnogo.api.domain.entity.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> findAll();

}
