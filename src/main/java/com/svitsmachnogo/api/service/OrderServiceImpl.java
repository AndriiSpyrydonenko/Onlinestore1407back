package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.OrderRepository;
import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.OrderDTO;
import com.svitsmachnogo.api.exceptions.NoSuchOrderException;
import com.svitsmachnogo.api.service.abstractional.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private Principal principal;


    public Order createOrderByCurrentUser(OrderDTO orderDTO){
        Order order = new Order();
        User currentUser = userService.findByEmail(principal.getName()).get();
        order.setUser(currentUser);
        order.setComment(orderDTO.getComment());
        order.setPayType(orderDTO.getPayType());
        order.setCreateDate(orderDTO.getCreateDate());
        order.setPackagingList(orderDTO.getPackagingList());
        return orderRepository.save(order);
    }

    public Order createOrderByUserId(OrderDTO orderDTO){
        Order order = new Order();
        User currentUser = userService.findById(orderDTO.getUserId()).get();
        order.setUser(currentUser);
        order.setComment(orderDTO.getComment());
        order.setPayType(orderDTO.getPayType());
        order.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        order.setPackagingList(orderDTO.getPackagingList());
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id) throws NoSuchOrderException {
        return orderRepository.findById(id).orElseThrow((() -> {return new NoSuchOrderException("no order!");}));
        //todo: add normal exception message
    }
    public List<Order> findAllByUserId(long userId){
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> findAllByCurrentUser(){
        User currentUser = userService.findByEmail(principal.getName()).get();
        return findAllByUserId(currentUser.getId());
    }
}
