package com.svitsmachnogo.api.service.order;

import com.svitsmachnogo.api.domain.dao.abstractional.OrderRepository;
import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.domain.entity.UserProfile;
import com.svitsmachnogo.api.dto.order.OrderDto;
import com.svitsmachnogo.api.exceptions.NoSuchOrderException;
import com.svitsmachnogo.api.service.cart.CartService;
import com.svitsmachnogo.api.service.user_profile.UserProfileService;
import com.svitsmachnogo.api.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service implementation for managing orders in the application.
 * This class provides functionality to handle orders.
 *
 * @author Vanya Demydenko
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserProfileService userProfileService;

    private final CartService cartService;

    /**
     * Creates an order for a user based on the provided OrderDto.
     *
     * @param orderDTO The OrderDto containing details to create an order.
     * @return The created Order entity.
     */
    public Order createOrderByUserId(OrderDto orderDTO) {
        UserProfile currentUser = userProfileService
                .findById(orderDTO.getUserId());//An exception is thrown if the user has been deleted and their jwt has not expired.

        Order order = DtoUtils.mapToOrder(currentUser, orderDTO);
        toAffectCartStateByOrder(orderDTO);
        return orderRepository.save(order);
    }

    /**
     * Updates the cart state by clearing the items in the cart and storing current customer data.
     * <p>
     * This method clears the items from the cart associated with the provided order
     * and updates the cart with the current customer data from the order details.
     *
     * @param orderDTO The OrderDto containing details to update the cart state.
     */
    private void toAffectCartStateByOrder(OrderDto orderDTO) {
        cartService.updateCustomer(orderDTO);
        cartService.removeAllFromCart(orderDTO.getUserId());
    }

    /**
     * Creates an order without associating it with a specific user.
     *
     * @param orderDTO The OrderDto containing details to create an order without a user.
     * @return The created Order entity.
     */
    public Order createOrderByNoUser(OrderDto orderDTO) {
        Order order = DtoUtils.mapToOrder(null, orderDTO);
        return orderRepository.save(order);
    }

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order to find.
     * @return The Order entity found based on the given ID.
     * @throws NoSuchOrderException If the order with the given ID is not found.
     */
    public Order findOrderById(Long id) throws NoSuchOrderException {
        return orderRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchOrderException(String.format("Order with id: %s is not exist.", id))
                );

    }

    /**
     * Finds all orders associated with a specific user.
     *
     * @param userId The ID of the user to find orders for.
     * @return A list of Order entities associated with the given user ID.
     */
    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    /**
     * Finds all orders .
     *
     * @return A list of Order entities.
     */
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
