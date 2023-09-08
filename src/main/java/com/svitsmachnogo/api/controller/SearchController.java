package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.GiftSetServiceImpl;
import com.svitsmachnogo.api.service.abstractional.CategoryService;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name="Search")
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GiftSetServiceImpl giftSetService;

    @Autowired
    ProductService productService;

    @GetMapping("/category/{id}")
    @Operation(summary = "Upon request id (category_id is a text string for example: nuts, oil, spices)" +
            " returns a list of goods belonging to the category with this id." +
            " For example: localhost8080://search/category/nuts will return a list of nuts")
    public List<ProductDTO> allProductByCategory(@PathVariable String id){
        List<Product> products = categoryService.getByIdAndReturnAllProducts(id);
        return ProductDTO.getList(products);
    }

    @GetMapping("/{partName}")
    @Operation(summary = "Returns a list of products whose name matches the search query")
    public List<ProductDTO> allProductsByCoincidence(@PathVariable String partName ){
        List<Product> products = productService.getByPartName(partName);
        return ProductDTO.getList(products);
    }

}
