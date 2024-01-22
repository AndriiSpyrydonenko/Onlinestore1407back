package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.dto.product.AddProductDto;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Product Controller", description = "Endpoints for managing products. This endpoint is only accessible to an administrator.")
@RequestMapping("/api/admin/product")
public class ProductController {

    private final ProductService productService;

    @PutMapping()
    public ResponseEntity<?> addProduct(@RequestBody AddProductDto product){
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
