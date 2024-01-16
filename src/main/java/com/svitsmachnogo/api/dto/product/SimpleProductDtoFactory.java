package com.svitsmachnogo.api.dto.product;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.dto.picture.PictureDTO;
import com.svitsmachnogo.api.dto.picture.PictureDtoFactory;

/**
 * A factory responsible for creating SimpleProductDto instances.
 * This factory is used to convert Product entities to SimpleProductDto instances,
 *
 * @author Vanya Demydenko
 */
public class SimpleProductDtoFactory implements DtoFactory<Product> {

    /**
     * Converts a Product entity to a SimpleProductDto instance.
     *
     * @param targetEntity The Product entity to be converted.
     * @return A SimpleProductDto representing the simplified view of the product.
     */
    @SuppressWarnings("unchecked")
    @Override
    public SimpleProductDto of(Product targetEntity) {
        SimpleProductDto dto = new SimpleProductDto();
        dto.setId(targetEntity.getId());
        dto.setName(targetEntity.getName());
        dto.setExist(targetEntity.isExist());
        dto.setGiftSet(targetEntity.isGiftSet());
        dto.setDiscountPercent(targetEntity.getDiscountPercent());
        dto.setRating(targetEntity.getRating());
        dto.setReviewCount(targetEntity.getReviewCount());
        dto.setUnit(targetEntity.getUnit());
        dto.setPackaging(targetEntity.getPackaging());
        dto.setMainPicture(getMainPictureIfExist(targetEntity));

        return dto;
    }

    /**
     * Retrieves the main picture for the Product entity if it exists.
     *
     * @param targetEntity The Product entity.
     * @return The main picture of the product if it exists; otherwise, returns null.
     */
    private PictureDTO getMainPictureIfExist(Product targetEntity){
        if(targetEntity.getPictures() != null && !targetEntity.getPictures().isEmpty()){
            return new PictureDtoFactory().of(targetEntity.getPictures().get(0));
        }
        return null;
    }

}
