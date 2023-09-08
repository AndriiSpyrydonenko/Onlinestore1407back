package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    @Transactional
    public List<Product> getByPriorityForMainPage() {
        List<Product> productsByPriority = productDAO.findByPriorityForMainPage();
        int limit = 25 - (productsByPriority.size());
        List<Product> productsByRating = productDAO.findAllByRatingWithLimit(limit);
        productsByPriority.addAll(productsByRating);
        return productsByPriority;
    }

    @Override
    @Transactional
    public List<Product> getByDiscountPercentForMainPage() {
       return productDAO.findForDiscountBlock();
    }

    @Override
    @Transactional
    public List<Product> getByPartName(String partName) {
        return productDAO.findByPartName(partName);
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Set<Product> getAllByCategoryId(String categoryId) {

        return productDAO.findAllByCategoryId(categoryId);
    }
}
