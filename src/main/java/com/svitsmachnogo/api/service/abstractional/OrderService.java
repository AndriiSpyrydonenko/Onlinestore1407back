package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.dto.order.OrderDto;
import com.svitsmachnogo.api.exceptions.NoSuchOrderException;

import java.util.List;

/**
 * Service interface defining operations related to managing orders in the application.
 * This interface provides specifications for handling orders.
 */
public interface OrderService {

    /**
     * Creates an order for a user based on the provided OrderDto.
     *
     * @param orderDTO The OrderDto containing details to create an order.
     * @return The created Order entity.
     */
    Order createOrderByUserId(OrderDto orderDTO);

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order to find.
     * @return The Order entity found based on the given ID.
     * @throws NoSuchOrderException If the order with the given ID is not found.
     */
    Order findOrderById(Long id) throws NoSuchOrderException;

    /**
     * Finds all orders associated with a specific user.
     *
     * @param userId The ID of the user to find orders for.
     * @return A list of Order entities associated with the given user ID.
     */
    List<Order> findAllByUserId(Long userId);

    /**
     * Creates an order without associating it with a specific user.
     *
     * @param orderDTO The OrderDto containing details to create an order without a user.
     * @return The created Order entity.
     */
    Order createOrderByNoUser(OrderDto orderDTO);
}

