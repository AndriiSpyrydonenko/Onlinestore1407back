package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Cart;
import com.svitsmachnogo.api.dto.cart.CartDto;
import com.svitsmachnogo.api.dto.cart.CartDtoFactory;
import com.svitsmachnogo.api.dto.cart.CartRequestDTO;
import com.svitsmachnogo.api.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/secure/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Endpoints for managing the user cart")
public class  CartController {

    private final CartService cartService;

    @PutMapping("/product")
    @Operation(summary = "Add product to cart", description = "Adds a product to the user's cart.Fully protected.")
    public ResponseEntity<?> addToCart(@RequestBody CartRequestDTO addToCartRequestDTO) {
        cartService.addToCart(addToCartRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product")
    @Operation(summary = "Remove product from cart", description = "Removes a product from the user's cart")
    public ResponseEntity<?> removeFromCart(@RequestBody CartRequestDTO cartRequestDTO) {
        cartService.removeFromCart(cartRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get cart by user ID", description = "Retrieves the user's cart by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CartDto.class))}),
            @ApiResponse(responseCode = "401", description = "If user unauthorized")
    })
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable(name = "id") Long id) {
        Cart cart = cartService.findById(id);
        CartDto cartDto = new CartDtoFactory().of(cart);
        return ResponseEntity.ok(cartDto);
    }
}
