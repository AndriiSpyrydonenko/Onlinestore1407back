package com.svitsmachnogo.api.service.product;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public List<Product> getByPriorityForMainPage() {
        List<Product> productsByPriority = productDAO.findByPriorityForMainPage();
        int limit = 25 - (productsByPriority.size());
        List<Product> productsByRating = productDAO.findAllByRatingWithLimit(limit);
        productsByPriority.addAll(productsByRating);
        return productsByPriority;
    }

    @Override
    public List<Product> getByDiscountPercentForMainPage() {
        return productDAO.findForDiscountBlock();
    }

    @Override
    public List<Product> getByPartName(String partName) {
        return productDAO.findByPartName(partName);
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Set<Product> getAllByCategoryId(String categoryId) {

        return productDAO.findAllByCategoryId(categoryId);
    }

    @Override
    public PriceFilter getDefaultPriceFilterByCategoryId(String categoryId) {
        return productDAO.findMinAndMaxPrice(categoryId);
    }

}
