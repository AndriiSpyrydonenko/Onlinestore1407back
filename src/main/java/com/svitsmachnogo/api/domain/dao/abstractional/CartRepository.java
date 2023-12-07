package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Repository for interacting with the carts_packaging table in the database.
 * This interface provides methods to work with the carts_packaging table.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart , Long> {

    /**
     * Removes a record from the carts_packaging table based on the provided parameters.
     *
     * @param userId    User ID.
     * @param productId Product ID.
     * @param amount    Product quantity.
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM carts_packaging WHERE user_id = :userId AND product_id = :productId AND amount = :amount")
    void removePackagingById(@Param("userId") Long userId,
                             @Param("productId") Integer productId,
                             @Param("amount") Integer amount);


    /**
     * Removes all user records from the carts_packaging table based on their ID.
     *
     * @param userId User ID.
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM carts_packaging WHERE user_id = :userId")
    void removeAllPackagingById(@Param("userId") Long userId);
}

