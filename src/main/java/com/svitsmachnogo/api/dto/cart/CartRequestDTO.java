package com.svitsmachnogo.api.dto.cart;

import lombok.Data;

/**
 * DTO (Data Transfer Object) representing a request for cart manipulation.
 */
@Data
public class CartRequestDTO {

    private Long cartId;

    private Integer productId;

    private Integer amount;

}
