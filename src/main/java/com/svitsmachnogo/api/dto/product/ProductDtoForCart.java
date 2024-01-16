package com.svitsmachnogo.api.dto.product;

import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.picture.PictureDTO;
import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import lombok.Data;

/**
 * Represents a DTO (Data Transfer Object) for product details used specifically in a cart.
 * Contains information about a product to be displayed in a cart, such as its name, article,
 * existence, main picture, packaging details, and unit.
 */
@Data
public class ProductDtoForCart implements AbstractDto {

    private Integer id;

    private String name;

    private int article;

    private boolean exist ;

    private PictureDTO mainPicture;

    private PackagingDto packagingDto;

    private String unit;

    /**
     * Protected constructor to restrict the creation of ProductDtoForCart instances.
     * Ensures that objects of this class can be created only by its child classes, specifically within its factory.
     */
    protected ProductDtoForCart() {
    }
}
