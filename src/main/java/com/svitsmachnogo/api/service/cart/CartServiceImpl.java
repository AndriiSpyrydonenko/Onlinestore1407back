package com.svitsmachnogo.api.service.cart;

import com.svitsmachnogo.api.domain.dao.abstractional.CartRepository;
import com.svitsmachnogo.api.domain.entity.Cart;
import com.svitsmachnogo.api.domain.entity.Packaging;
import com.svitsmachnogo.api.domain.entity.PackagingId;
import com.svitsmachnogo.api.dto.cart.CartRequestDTO;
import com.svitsmachnogo.api.dto.order.OrderDto;
import com.svitsmachnogo.api.exceptions.NoSuchCartException;
import com.svitsmachnogo.api.service.abstractional.PackagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * Implementation of CartService defining operations related to managing user carts in the application.
 * This service handles cart functionalities such as adding/removing items, updating user details in the cart, etc.
 *
 * @author Vanya Demydenko
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final PackagingService packagingService;

    /**
     * Adds a packaging item to the cart based on the provided CartRequestDTO.
     * It retrieves the Cart entity by ID, then finds and adds the Packaging entity to the cart.
     * The updated cart is then saved back to the repository.
     *
     * @param addToCartRequestDTO The CartRequestDTO containing details to add an item to the cart.
     */
    public void addToCart(CartRequestDTO addToCartRequestDTO) {
        Cart cart = findById(addToCartRequestDTO.getCartId());

        Packaging packaging = packagingService
                .findById(mapToPackagingId(addToCartRequestDTO))
                .orElseThrow(throwException(addToCartRequestDTO));

        cart.getPackagingList().add(packaging);

        cartRepository.saveAndFlush(cart);
    }

    /**
     * Removes a packaging item from the cart based on the provided CartRequestDTO.
     * It removes the specified Packaging entity from the cart using repository methods.
     *
     * @param cartRequestDTO The CartRequestDTO containing details to remove an item from the cart.
     */
    public void removeFromCart(CartRequestDTO cartRequestDTO) {
        cartRepository.removePackagingById(
                cartRequestDTO.getCartId(),
                cartRequestDTO.getProductId(),
                cartRequestDTO.getAmount());
    }

    /**
     * Removes all packaging items from the cart associated with the given user ID.
     * It removes all Packaging entities related to the specified user from the cart using repository methods.
     *
     * @param userId The ID of the user whose cart items need to be removed.
     */
    public void removeAllFromCart(Long userId) {
        cartRepository.removeAllPackagingById(userId);
    }

    /**
     * Finds a cart by its ID.
     * It retrieves the Cart entity by ID from the repository.
     *
     * @param id The ID of the cart to find.
     * @return The Cart entity found based on the given ID.
     * @throws NoSuchCartException If the cart with the given ID is not found.
     */
    public Cart findById(Long id) {
        return cartRepository
                .findById(id)
                .orElseThrow(throwException(id));
    }

    /**
     * Updates the customer details in the cart based on the provided OrderDto.
     * It retrieves the Cart entity by user ID, then updates the customer details in the cart.
     * The updated cart information is then saved back to the repository.
     *
     * @param orderDto The OrderDto containing details to update the customer information in the cart.
     */
    public void updateCustomer(OrderDto orderDto) {
        Cart cart = findById(orderDto.getUserId());
        cart.setCustomerName(orderDto.getCustomerName());
        cart.setCustomerSurname(orderDto.getCustomerSurname());
        cart.setCustomerAddress(orderDto.getCustomerAddress());
        cart.setCustomerPhoneNumber(orderDto.getCustomerPhoneNumber());
        cartRepository.saveAndFlush(cart);
    }

    private PackagingId mapToPackagingId(CartRequestDTO addToCartRequestDTO) {
        return new PackagingId(
                addToCartRequestDTO.getProductId(),
                addToCartRequestDTO.getAmount());
    }

    private Supplier<NoSuchCartException> throwException(Long userId) {
        return () -> new NoSuchCartException(String
                .format("User with id:%s does not already exist", userId));
    }

    private Supplier<NoSuchElementException> throwException(CartRequestDTO addToCartRequestDTO) {
        return () -> new NoSuchElementException(String
                .format("Packaging with amount : %s  dose not already exist for product with id %s."
                        , addToCartRequestDTO.getAmount()
                        , addToCartRequestDTO.getProductId()));
    }
}
