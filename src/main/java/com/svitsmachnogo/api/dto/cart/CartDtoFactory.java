package com.svitsmachnogo.api.dto.cart;

import com.svitsmachnogo.api.domain.entity.Cart;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.utils.DtoUtils;

/**
 * Factory class responsible for creating CartDto instances from Cart entities.
 * This class implements the DtoFactory interface for Cart objects.
 * <p>
 * It provides a method to transform a Cart entity into its corresponding CartDto representation.
 * <p>
 * @see Cart
 * @see CartDto
 * @see DtoFactory
 * @see DtoUtils
 */
public class CartDtoFactory extends CartDto implements DtoFactory<Cart> {

    /**
     * Converts a Cart entity to a CartDto object.
     *
     * @param targetEntity The Cart entity to be transformed into a CartDto.
     * @return The corresponding CartDto representation of the Cart entity.
     */
    @Override
    public CartDto of(Cart targetEntity) {
        CartDto dto = new CartDto();
        dto.setId(targetEntity.getId());
        dto.setCustomerName(targetEntity.getCustomerName());
        dto.setCustomerSurname(targetEntity.getCustomerSurname());
        dto.setCustomerAddress(targetEntity.getCustomerAddress());
        dto.setCustomerPhoneNumber(targetEntity.getCustomerPhoneNumber());
        dto.setProducts(DtoUtils.cartProductsListOf(targetEntity.getPackagingList()));
        return dto;
    }
}
