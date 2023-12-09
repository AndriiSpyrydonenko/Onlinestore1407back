package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.dto.order.FactoryType;
import com.svitsmachnogo.api.dto.order.OrderDto;
import com.svitsmachnogo.api.dto.order.OrderDtoFactory;
import com.svitsmachnogo.api.dto.order.request_dto.RequestOrderDto;
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

    //todo: change order to orders in the which mappings

    @Operation(summary = "Get order by ID.Is protected!")
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") Long id) throws NoSuchOrderException {
        return ResponseEntity.ok(OrderDtoFactory
                .crateFactory(FactoryType.RESPONSE)
                .of(orderService.findOrderById(id)));
    }

    @Operation(summary = "Get all orders.")
    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> getAllOrder() throws NoSuchOrderException {
        return ResponseEntity.ok(DtoUtils.listOf(
                orderService.findAll(),
                OrderDtoFactory.crateFactory(FactoryType.RESPONSE)));
    }

    @Operation(summary = "Get orders by user ID.Is protected!")
    @GetMapping("/order/user/{id}/orders")
    public ResponseEntity<List<OrderDto>> getOrderByUserId(@PathVariable(name = "id") Long id) throws NoSuchOrderException {
        DtoFactory<Order> factory = OrderDtoFactory.crateFactory(FactoryType.RESPONSE);
        List<OrderDto> orderDTOList = DtoUtils.listOf(orderService.findAllByUserId(id), factory);
        return ResponseEntity.ok(orderDTOList);
    }

    @Operation(summary = "Create order by user ID.Is protected!")
    @PutMapping("/order/user")
    public ResponseEntity<?> createOrderByUserId(@RequestBody RequestOrderDto orderDTO) {
        orderService.createOrderByUserId(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Create order by unauthorized user", description = "userId field must be null")
    @PutMapping("/unauthorized-user")
    public ResponseEntity<?> createOrderByNoUser(@RequestBody RequestOrderDto orderDTO) {
        orderService.createOrderByNoUser(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
