package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> findAll();

    Category findByIdWithProduct(String id);

}
