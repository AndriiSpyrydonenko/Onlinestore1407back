package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Order entities.
 *
 * @author Vanya Demydenko
 */
@Repository
public interface
OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds all orders associated with a specific user ID.
     *
     * @param id The ID of the user.
     * @return The list of orders associated with the given user ID.
     */
    @Query("from Order o LEFT JOIN FETCH o.userProfile where o.userProfile.id = :id")
    List<Order> findAllByUserId(Long id);

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order.
     * @return An Optional containing the found Order entity, or empty if not found.
     */
    @Override
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.userProfile where o.id = :id")
    Optional<Order> findById(Long id);

    /**
     * Finds all orders.
     *
     * @return The List containing the found Order entity.
     */
    @Override
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.userProfile")
    List<Order> findAll();
}

