package com.svitsmachnogo.api.dto.product;

import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import com.svitsmachnogo.api.dto.picture.PictureDTO;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class AddProductDto {

    private String category;

    private int article;

    private String name;

    private String description;

    private String bigDescription;

    private String nutritionalValue;

    private String usage;

    private String countryProducer;

    private boolean exist = true;

    private int discountPercent = 0;

    private int quantity ;

    private String unit;

    private String subcategoryId;

    private String subcategoryName;

    private String subcategoryTitle;

    private List<PackagingDto> packagings;

    private List<PictureDTO> pictures;

}
