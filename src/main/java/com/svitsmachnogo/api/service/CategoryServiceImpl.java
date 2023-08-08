package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.CategoryDAO;
import com.svitsmachnogo.api.domain.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> findAllForMainPage() {
        return categoryDAO.findAll();
    }
}
