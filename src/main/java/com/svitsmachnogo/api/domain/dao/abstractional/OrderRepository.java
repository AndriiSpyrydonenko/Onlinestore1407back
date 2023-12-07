package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Order entities.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds all orders associated with a specific user ID.
     *
     * @param id The ID of the user.
     * @return The list of orders associated with the given user ID.
     */
    @Query("from Order o where o.userProfile.id = :id")
    List<Order> findAllByUserId(Long id);

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order.
     * @return An Optional containing the found Order entity, or empty if not found.
     */
    @Override
    @Query("from Order o where o.id= :id")
    Optional<Order> findById(Long id);
}

