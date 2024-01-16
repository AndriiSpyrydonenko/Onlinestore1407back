package com.svitsmachnogo.api.dto.packaging;

import com.svitsmachnogo.api.dto.AbstractDto;
import lombok.Data;

/**
 * Represents a Data Transfer Object (DTO) for Packaging details.
 * This DTO contains information about the packaging of a product.
 */
@Data
public class PackagingDto implements AbstractDto {

    protected Integer productId;

    protected Integer amount;

    protected Double cost;

    /**
     * Protected no-argument constructor.
     * This constructor is intentionally protected to limit instantiation outside this package
     * and to encourage creation of instances only through factory subclasses.
     */
    protected PackagingDto() {
    }

}
