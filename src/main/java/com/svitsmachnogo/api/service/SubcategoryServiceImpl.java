package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.SubcategoryDAO;
import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.domain.entity.embedded_entity.BlockOfCriteria;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    SubcategoryDAO subcategoryDAO;

    private Set<Product> products;

    private List<Subcategory> subcategories;

    @Override
    @Transactional
    public Set<Product> getProductsBySubcategory(String subcategoryId) {
        return subcategoryDAO.findById(subcategoryId).getProducts();
    }

    @Override
    @Transactional
    public List<Subcategory> getAllForCategoryPage(String categoryId) {
        return subcategoryDAO.findAllByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public List<BlockOfCriteria> buildBlockForCategoryPage(String categoryId) {
        List<Subcategory> subcategories = getAllForCategoryPage(categoryId);
        setCountProductsForSubcategory(subcategories);
        Set<String> titles = getTitleFromSubcategory(subcategories);
        return buildBlockOfCriteria(subcategories, titles);
    }

    private static void setCountProductsForSubcategory(List<Subcategory> subcategories) {
        subcategories.forEach(s -> s.setProductCount(s.getProducts().size()));
    }

    private static List<BlockOfCriteria> buildBlockOfCriteria(List<Subcategory> subcategories, Set<String> titles) {
        return titles
                .stream()
                .map(t -> BlockOfCriteria.create(t, getSubcategories(subcategories, t)))
                .collect(Collectors.toList());
    }

    private static List<Subcategory> getSubcategories(List<Subcategory> subcategories, String t) {
        return subcategories
                .stream()
                .filter(s -> s.getTitle().equals(t))
                .collect(Collectors.toList());
    }

    private static Set<String> getTitleFromSubcategory(List<Subcategory> subcategories) {
        return subcategories
                .stream()
                .map(Subcategory::getTitle)
                .collect(Collectors.toSet());
    }

    public Set<Product> saveAndGetProductsList(String subcategoryId) {
        setProducts(getProductsBySubcategory(subcategoryId));
        return getProducts();// todo: change conception
    }

    @Override
    public Set<Product> productFilteringBySubcategory(List<Subcategory> subcategories) {
        return null;
    }

    @Override
    public Set<Product> refreshFilterState(String subcategoryId, boolean isActive) {
        Subcategory subcategoryHolder = new Subcategory();
        for (Subcategory subcategory : subcategories) {
            if (subcategory.getId().equals(subcategoryId)) {
                subcategoryHolder = subcategory;
                subcategoryHolder.setActive(isActive);
            }
        }

        for(    )
        return getProducts();
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
