package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.CategoryDAO;
import com.svitsmachnogo.api.domain.dao.abstractional.CategoryRepository;
import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.service.abstractional.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<Category> findAllForMainPage() {
        return categoryDAO.findAll();
    }

    @Override
    @Transactional
    public List<Product> getByIdAndReturnAllProducts(String id) {
        Category category = categoryDAO.findByIdWithProduct(id);
        return category.getProducts();
    }

    @Override
    public Category findById(String categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow();
    }
}
