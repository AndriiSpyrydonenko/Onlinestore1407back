package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("from Order o where o.user.id = :id")
    List<Order> findAllByUserId( Long id);

    @Override
    @Query("from Order o where o.id= :id")
    Optional<Order> findById(Long id);
}
