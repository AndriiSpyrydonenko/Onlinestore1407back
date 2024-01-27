package com.svitsmachnogo.api.service.product;

import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.domain.entity.Picture;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import com.svitsmachnogo.api.dto.product.AddProductDto;
import com.svitsmachnogo.api.dto.subcategory.SubcategorySimpleDto;
import com.svitsmachnogo.api.service.abstractional.CategoryService;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ManageProductServiceImpl implements ManageProductService {

    private final ProductDAO productDAO;

    private final CategoryService categoryService;

    private final SubcategoryService subcategoryService;

    @Override
    @Transactional
    public void addProduct(AddProductDto productDto) {
        Category category = categoryService.findById(productDto.getCategoryId());

        Product product = mapToProduct(productDto, category); // new product without id

        product = productDAO.saveProduct(product); // save to the DB and get the id from it

        findAllSubcategoriesByIds(productDto, product)
                .forEach(subcategoryService::save); // save subcategories with new product
    }

    private Product mapToProduct(AddProductDto productDto, Category category) {
        Product product = new Product();

        product.setCategory(category);
        product.setArticle(productDto.getArticle());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBigDescription(productDto.getBigDescription());
        product.setNutritionalValue(productDto.getNutritionalValue());
        product.setUsage(productDto.getUsage());
        product.setCountryProducer(productDto.getCountryProducer());
        product.setArticle(productDto.getArticle());
        product.setExist(isExist(productDto));
        product.setDiscountPercent(productDto.getDiscountPercent());
        product.setQuantity(productDto.getTotalQuantity());
        product.setUnit(productDto.getUnit());
        product.setCreateDate(new Timestamp(System.currentTimeMillis()));
        product.setRating(0.0);
        product.setReviewCount(0);
        product.setNumberOfOrders(0);
        product.setMinPrice(getMinPrice(productDto));
        product.setPackaging(mapToPackagings(productDto));
        product.setPictures(mapToPictures(productDto, product));
        return product;
    }

    private boolean isExist(AddProductDto product) {
        Integer maxAmount = product.getPackagings()
                .stream()
                .map(PackagingDto::getAmount)
                .max(Integer::compareTo)
                .orElseThrow(throwException(product.getName()));
        return maxAmount < product.getTotalQuantity();
    }

    private List<Subcategory> findAllSubcategoriesByIds(AddProductDto productDto, Product product) {
        return productDto
                .getSubcategories()
                .stream()
                .map(s -> getSubcategory(s, product))
                .collect(Collectors.toList());
    }

    private Subcategory getSubcategory(SubcategorySimpleDto subcategoryDto, Product product) {
        Subcategory subcategory = subcategoryService
                .findById(subcategoryDto.getSubcategoryId())
                .orElse(getNewSubcategory(subcategoryDto, product.getCategory()));

        subcategory.getProducts().add(product);

        return subcategory;
    }

    private Subcategory getNewSubcategory(SubcategorySimpleDto subcategoryDto, Category category) {
        Subcategory subcategory = new Subcategory();
        subcategory.setProducts(new HashSet<>());
        subcategory.setCategory(category);
        subcategory.setId(subcategoryDto.getSubcategoryId());
        subcategory.setName(subcategoryDto.getSubcategoryName());
        subcategory.setTitle(subcategoryDto.getSubcategoryTitle());
        return subcategory;
    }

    private Map<Integer, Double> mapToPackagings(AddProductDto product) {
        return product.getPackagings()
                .stream()
                .collect(Collectors
                        .toMap(PackagingDto::getAmount, PackagingDto::getCost));
    }

    private List<Picture> mapToPictures(AddProductDto productDto, Product product) {
        return productDto.getPictures()
                .stream()
                .map(u -> new Picture(u, product))
                .collect(Collectors.toList());
    }

    private Double getMinPrice(AddProductDto product) {
        return product
                .getPackagings()
                .stream()
                .min(Comparator.comparingInt(PackagingDto::getAmount))
                .orElseThrow(throwException(product.getName()))
                .getCost();
    }

    private static Supplier<RuntimeException> throwException(String productName) {
        return () -> new RuntimeException(
                String.format("The product '%s' does not have packaging.", productName));
    }

}
