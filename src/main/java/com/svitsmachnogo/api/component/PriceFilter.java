package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.exceptions.WrongPriceFilterException;
import org.springframework.stereotype.Component;

@Component
public class PriceFilter {

    private Double minPrice;

    private Double maxPrice;

    private String categoryId;

    public PriceFilter() {
    }

    public PriceFilter(double minPrice, double maxPrise) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrise;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}