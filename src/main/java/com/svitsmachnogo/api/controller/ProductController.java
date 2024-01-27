package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.dto.product.AddProductDto;
import com.svitsmachnogo.api.dto.subcategory.SubcategoryDTO;
import com.svitsmachnogo.api.dto.subcategory.SubcategorySimpleDto;
import com.svitsmachnogo.api.dto.subcategory.SubcategorySimpleDtoFactory;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import com.svitsmachnogo.api.service.product.ManageProductService;
import com.svitsmachnogo.api.utils.DtoUtils;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping("/products/subcategories")
    public ResponseEntity<List<SubcategorySimpleDto>> getSubcategoriesByCategoryId(
            @RequestParam(name = "categoryId")
            @Parameter(name = "categoryId" ,required = true ,description = "The category id of target product")
            String categoryId){

        List<Subcategory> subcategories = subcategoryService.getAllSubcategoryByCategoryId(categoryId);
        return ResponseEntity.ok(DtoUtils.listOf(subcategories,new SubcategorySimpleDtoFactory()));
    }
}
