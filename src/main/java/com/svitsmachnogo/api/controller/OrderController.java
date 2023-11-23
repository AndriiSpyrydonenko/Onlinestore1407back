package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.dto.OrderDTO;
import com.svitsmachnogo.api.exceptions.NoSuchOrderException;
import com.svitsmachnogo.api.service.OrderServiceImpl;
import jakarta.validation.constraints.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImpl orderService;

    @GetMapping("/byId/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "id") Long id) throws NoSuchOrderException {
        OrderDTO orderDTO = OrderDTO.of(orderService.findOrderById(id));
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/byUserId/{id}")
    public ResponseEntity<List<OrderDTO>> getOrderByUserId(@PathVariable(name = "id") Long id) throws NoSuchOrderException {
        List<OrderDTO> orderDTOList = OrderDTO.listOf(orderService.findAllByUserId(id));
        return ResponseEntity.ok(orderDTOList);
    }

    @PostMapping("/createByCurrentUser")
    public ResponseEntity<?> createOrderByCurrentUser(@RequestBody OrderDTO orderDTO){
        orderService.createOrderByCurrentUser(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/createByUserId")
    public ResponseEntity<?> createOrderByUserId(@RequestBody OrderDTO orderDTO){
        orderService.createOrderByUserId(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
