package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.exceptions.WrongPriceFilterException;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PriceFilter {

    private Double minPrice;

    private Double maxPrice;

    private String categoryId;

    public PriceFilter() {
    }


}