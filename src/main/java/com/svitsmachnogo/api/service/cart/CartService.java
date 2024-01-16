package com.svitsmachnogo.api.service.cart;

import com.svitsmachnogo.api.domain.entity.Cart;
import com.svitsmachnogo.api.dto.cart.CartRequestDTO;
import com.svitsmachnogo.api.dto.order.OrderDto;

/**
 * Service interface defining operations related to managing carts in the application.
 * This interface provides specifications for working with shopping carts.
 */
public interface CartService {

    /**
     * Adds an item to the user's cart based on the provided CartRequestDTO.
     *
     * @param cartRequestDTO The CartRequestDTO containing details of the item to add.
     */
    void addToCart(CartRequestDTO cartRequestDTO);

    /**
     * Removes an item from the user's cart based on the provided CartRequestDTO.
     *
     * @param cartRequestDTO The CartRequestDTO containing details of the item to remove.
     */
    void removeFromCart(CartRequestDTO cartRequestDTO);

    /**
     * Removes all items from the cart for the specified user.
     *
     * @param userId The ID of the user whose cart items need to be removed.
     */
    void removeAllFromCart(Long userId);

    /**
     * Finds a cart by its ID.
     *
     * @param id The ID of the cart to find.
     * @return The Cart object found based on the given ID.
     */
    Cart findById(Long id);

    /**
     * Updates customer information based on the provided OrderDto.
     *
     * @param orderDto The OrderDto containing customer information to update.
     */
    void updateCustomer(OrderDto orderDto);
}
