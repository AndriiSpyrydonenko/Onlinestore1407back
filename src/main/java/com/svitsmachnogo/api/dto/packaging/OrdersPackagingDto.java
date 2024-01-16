package com.svitsmachnogo.api.dto.packaging;

import com.svitsmachnogo.api.dto.AbstractDto;
import lombok.Data;

/**
 * Represents a Data Transfer Object (DTO) for Packaging details.
 * This DTO contains information about the packaging of a product.
 */
@Data
public class OrdersPackagingDto implements AbstractDto {

    private Integer amountOfUnits;

    protected Integer productId;

    protected Integer amount;

    protected Double cost;
}
