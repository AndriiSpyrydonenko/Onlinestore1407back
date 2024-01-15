package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.product.SimpleProductDto;
import com.svitsmachnogo.api.dto.product.SimpleProductDtoFactory;
import com.svitsmachnogo.api.service.user_profile.UserProfileService;
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
@Tag(name = "Wishlist", description = "Endpoints for managing user wishlist.Fully protected")
@RequestMapping("/api/secure/wishlist")
public class WishListController {

    private final UserProfileService userProfileService;

    @Operation(summary = "Add product to wishlist")
    @PutMapping("/product")
    public ResponseEntity<?> addToWishlist(@RequestBody WishListRequestDto wishListDto) {
        userProfileService.addToWishList(wishListDto.userId(), wishListDto.productId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Delete product from wishlist")
    @DeleteMapping("/product")
    public ResponseEntity<?> deleteFromWishlist(@RequestBody WishListRequestDto wishListDto) {
        userProfileService.removeFromWishList(wishListDto.userId(), wishListDto.productId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get wishlist products by user ID")
    @GetMapping("/user/{id}/products")
    public ResponseEntity<List<SimpleProductDto>> getWishlist(@PathVariable(name = "id") Long id) {
        List<Product> wishlist = userProfileService.getWishListByUserId(id);
        return ResponseEntity.ok(DtoUtils.listOf(wishlist, new SimpleProductDtoFactory()));
    }
}

record WishListRequestDto(Long userId, Integer productId) {// dto for request body
}

