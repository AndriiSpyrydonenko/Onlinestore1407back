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

    //    public Double getMinPrice() {
//        return minPrice;
//    }
//
//    public void setMinPrice(Double minPrice) throws WrongPriceFilterException {
//        if(minPrice < maxPrice && minPrice >= 0 ) {
//            this.minPrice = minPrice;
//        }else {
//            throw new WrongPriceFilterException("Сannot assign such a price: "+minPrice+" because it is less than zero or more than the maximum");
//        }
//
//    }
//
//    public Double getMaxPrice() {
//        return maxPrice;
//    }
//
//    public void setMaxPrice(Double maxPrice) throws WrongPriceFilterException {
//        if(maxPrice > minPrice && maxPrice > 0 ) {
//            this.maxPrice = maxPrice;
//        }else {
//            throw new WrongPriceFilterException("cannot assign such a price: "+maxPrice+" because it is less than zero or less than the minimum price");
//        }
//    }
}