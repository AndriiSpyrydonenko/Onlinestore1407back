package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.dto.order.FactoryType;
import com.svitsmachnogo.api.dto.order.OrderDto;
import com.svitsmachnogo.api.dto.order.OrderDtoFactory;
import com.svitsmachnogo.api.dto.order.request_dto.RequestGuestOrderDto;
import com.svitsmachnogo.api.dto.order.request_dto.RequestUserOrderDto;
import com.svitsmachnogo.api.exceptions.NoSuchOrderException;
import com.svitsmachnogo.api.service.order.OrderService;
import com.svitsmachnogo.api.utils.DtoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Order Controller", description = "Endpoints for managing orders")
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get order by ID.Is protected!")
    @GetMapping("/secure/orders/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") Long id) throws NoSuchOrderException {
        return ResponseEntity.ok(OrderDtoFactory
                .crateFactory(FactoryType.RESPONSE)
                .of(orderService.findOrderById(id)));
    }

    @Operation(summary = "Get all orders.Is protected! Only for admin.")
    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderDto>> getAllOrder() throws NoSuchOrderException {
        return ResponseEntity.ok(DtoUtils.listOf(
                orderService.findAll(),
                OrderDtoFactory.crateFactory(FactoryType.RESPONSE)));
    }

    @Operation(summary = "Get orders by user ID.Is protected!")
    @GetMapping("/secure/orders/user/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable(name = "id") Long id) throws NoSuchOrderException {
        DtoFactory<Order> factory = OrderDtoFactory.crateFactory(FactoryType.RESPONSE);
        List<OrderDto> orderDTOList = DtoUtils.listOf(orderService.findAllByUserId(id), factory);
        return ResponseEntity.ok(orderDTOList);
    }

    @Operation(summary = "Create order by user ID.Is protected!")
    @PutMapping("/secure/orders/user")
    public ResponseEntity<?> createOrderByUserId(@RequestBody RequestUserOrderDto orderDTO) {
        orderService.createOrderByUserId(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Create order by unauthorized user")
    @PutMapping("/orders/unauthorized-user")
    public ResponseEntity<?> createOrderByNoUser(@RequestBody RequestGuestOrderDto orderDTO) {
        orderService.createOrderByNoUser(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
