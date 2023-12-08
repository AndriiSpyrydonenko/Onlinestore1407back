package com.svitsmachnogo.api.dto.cart;

import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.product.ProductDtoForCart;
import lombok.Data;

import java.util.List;

/**
 * Represents a DTO (Data Transfer Object) for Cart entities.
 * This class is used as a base class to define CartDto instances.
 * <p>
 * This class has a protected constructor, which restricts object instantiation to its subclasses.
 * By having a protected constructor, it ensures that CartDto objects can only be created by its subclasses,
 * such as in the CartDtoFactory class.
 * <p>
 * @see CartDtoFactory
 *
 * @author Vanya Demydenko
 */
@Data
public class CartDto implements AbstractDto {

    private Long id;

    private String customerName;

    private String customerSurname;

    private String customerPhoneNumber;

    private String customerAddress;

    private List<ProductDtoForCart> products; // product list that contains in cards

    /**
     * Default constructor for CartDto.
     * It restricts the instantiation of objects to its subclasses.
     */
    protected CartDto() {
    }
}

