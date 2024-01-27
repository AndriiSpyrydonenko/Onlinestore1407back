package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.SubcategoryDAO;
import com.svitsmachnogo.api.domain.dao.abstractional.SubcategoryRepository;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {


    private final SubcategoryDAO subcategoryDAO;

    private final SubcategoryRepository subcategoryRepository;

    @Override
    @Transactional
    public Set<Product> getProductsBySubcategory(String subcategoryId) {
        return subcategoryDAO
                .findById(subcategoryId)
                .orElseThrow()
                .getProducts();
    }

    @Override
    @Transactional
    public List<Subcategory> getAllSubcategoryByCategoryId(String categoryId) {
        List<Subcategory> subcategories = subcategoryDAO.findAllByCategoryId(categoryId);
        setCountProductsForSubcategory(subcategories);
        return subcategories;
    }

    @Override
    @Transactional
    public Optional<Subcategory> findById(String id) {
        return subcategoryRepository.findById(id);
    }

    private static void setCountProductsForSubcategory(List<Subcategory> subcategories) {
        subcategories.forEach(s -> s.setProductCount(s.getProducts().size()));
    }

    @Override
    @Transactional
    public void save(Subcategory subcategory) {
        subcategoryRepository.save(subcategory);
    }
}
