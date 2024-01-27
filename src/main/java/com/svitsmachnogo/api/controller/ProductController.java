package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.dto.product.AddProductDto;
import com.svitsmachnogo.api.dto.subcategory.SubcategoryDTO;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import com.svitsmachnogo.api.service.product.ManageProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Product Controller", description = "Endpoints for managing products. This endpoint is only accessible to an administrator.")
@RequestMapping("/api/test/products")
public class ProductController {

    private final ManageProductService productService;

    private final SubcategoryService subcategoryService;

    @PutMapping()
    public ResponseEntity<?> addProduct(@RequestBody AddProductDto product){
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/products/subcategory/{categoryId}")
    public ResponseEntity<List<SubcategoryDTO>> getSubcategoriesByCategoryId(@PathVariable String categoryId){
        subcategoryService.getAllSubcategoryByCategoryId(categoryId);
    }
}
