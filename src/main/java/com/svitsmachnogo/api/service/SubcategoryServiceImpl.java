package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.SubcategoryDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryDAO subcategoryDAO;

    @Override
    @Transactional
    public Set<Product> getProductsBySubcategory(String subcategoryId) {
        return subcategoryDAO.findById(subcategoryId).getProducts();
    }

    @Override
    @Transactional
    public List<Subcategory> getAllSubcategoryByCategoryId(String categoryId) {
        List<Subcategory> subcategories = subcategoryDAO.findAllByCategoryId(categoryId);
        setCountProductsForSubcategory(subcategories);
        return subcategories;
    }

    private static void setCountProductsForSubcategory(List<Subcategory> subcategories) {
        subcategories.forEach(s -> s.setProductCount(s.getProducts().size()));
    }
}
