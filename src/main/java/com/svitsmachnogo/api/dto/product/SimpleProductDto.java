package com.svitsmachnogo.api.dto.product;

import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.picture.PictureDTO;
import lombok.Data;

import java.util.Map;

/**
 * This class represents a Simple Product DTO (Data Transfer Object), providing common details about a product.
 * It offers a general view of products on pages.
 * The SimpleProductDto includes basic information about a product, such as its ID, name, existence, rating,
 * review count, discount percent, unit, main picture, and packaging details.
 */
@Data
public class SimpleProductDto implements AbstractDto {

    private Integer id;

    private String name;

    private boolean exist = true;

    private boolean isGiftSet = false;

    private double rating;

    private int reviewCount;

    private int discountPercent = 0;

    private String unit;

    private PictureDTO mainPicture;

    private Map<Integer ,Double> packaging;
}
