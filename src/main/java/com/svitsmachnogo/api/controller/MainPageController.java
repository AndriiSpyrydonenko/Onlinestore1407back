package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.domain.entity.GiftSet;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.CategoryDTO;
import com.svitsmachnogo.api.dto.ProductDTO;
import com.svitsmachnogo.api.service.GiftSetServiceImpl;
import com.svitsmachnogo.api.service.abstractional.CategoryService;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Endpoints for main page")
@RequestMapping("/api/main_page")
public class MainPageController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GiftSetServiceImpl giftSetService;

    @Autowired
    ProductService productService;

    @GetMapping("/categories")
    @Operation(summary = "returns a list of categories." +
            " Each category has a name in Ukrainian, English (id), 2 links to icons (hover and fill)")
    public List<CategoryDTO> categoriesForMainPage() {
        List<Category> categories = categoryService.findAllForMainPage();
        return CategoryDTO.getList(categories);
    }

    @GetMapping("/gift_set")
    @Operation(summary = "Returns id (always 1), link to picture, discount percentage")
    public GiftSet setGiftSetForMainPage() {
        return giftSetService.getForMainPage();
    }

    @GetMapping("/products")
    @Operation(summary = "Returns a list of products to display on the main page (25 items)")
    public List<ProductDTO> setProductsToPriorityBlock() {
        List<Product> products = productService.getByPriorityForMainPage();
        return ProductDTO.getList(products);
    }

    @GetMapping("/discount_products")
    @Operation(summary = "returns a list of cards with id, picture, name and discount percentage." +
            " All other fields - null")
    public List<ProductDTO> setDiscountBlock() {
        List<Product> products = productService.getByDiscountPercentForMainPage();
        return ProductDTO.getListForDiscountBlock(products);
    }

    @GetMapping("/quick_view/{product_id}")
    @Operation(summary = "Returns all information about the product, for display in the quick view")
    public ProductDTO productForQuickView(@Parameter(description = "The ID of the product that will be displayed in the quick view")
                                          @PathVariable int product_id) {
        Product product = productService.getProductById(product_id);
        return ProductDTO.createForQuickView(product);
    }
}
