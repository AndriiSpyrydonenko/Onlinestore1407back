package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.entity.Category;
import com.svitsmachnogo.api.domain.entity.Picture;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import com.svitsmachnogo.api.dto.product.AddProductDto;
import com.svitsmachnogo.api.dto.subcategory.AddSubcategoryDto;
import com.svitsmachnogo.api.service.abstractional.CategoryService;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    private final CategoryService categoryService;

    private final SubcategoryService subcategoryService;

    @Override
    @Transactional
    public List<Product> getByPriorityForMainPage() {
        List<Product> productsByPriority = productDAO.findByPriorityForMainPage();
        int limit = 25 - (productsByPriority.size());
        List<Product> productsByRating = productDAO.findAllByRatingWithLimit(limit);
        productsByPriority.addAll(productsByRating);
        return productsByPriority;
    }

    @Override
    @Transactional
    public List<Product> getByDiscountPercentForMainPage() {
        return productDAO.findForDiscountBlock();
    }

    @Override
    @Transactional
    public List<Product> getByPartName(String partName) {
        return productDAO.findByPartName(partName);
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Set<Product> getAllByCategoryId(String categoryId) {

        return productDAO.findAllByCategoryId(categoryId);
    }

    @Override
    public PriceFilter getDefaultPriceFilterByCategoryId(String categoryId) {
        return productDAO.findMinAndMaxPrice(categoryId);
    }

    @Override
    @Transactional
    public void addProduct(AddProductDto productDto) {
        Category category = categoryService.findById(productDto.getCategoryId());
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
        product.setExist(isExit(productDto));
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

        List<Subcategory> subcategories = findAllSubcategoriesByIds(productDto,product);

        subcategories.forEach(s -> s.getProducts().add(product));

        productDAO.saveProduct(product);
    }

    private boolean isExit(AddProductDto product) {
        Integer maxAmount = product.getPackagings()
                .stream()
                .map(PackagingDto::getAmount)
                .max(Integer::compareTo)
                .get();

        return maxAmount < product.getTotalQuantity();
    }

    private List<Subcategory> findAllSubcategoriesByIds(AddProductDto productDto, Product product) {
//        return productDto.getSubcategories()
//                .stream()
//                .map(AddSubcategoryDto::getSubcategoryId)
//                .map(subcategoryService::findById)
//                .collect(Collectors.toList());
        return productDto
                .getSubcategories()
                .stream()
                .map(s -> getSubcategory(s, product))
                .collect(Collectors.toList());
    }

    private Subcategory getSubcategory(AddSubcategoryDto subcategoryDto, Product product) {
        return subcategoryService
                .findById(subcategoryDto.getSubcategoryId())
                .orElse(getNewSubcategory(subcategoryDto, product));
    }

    private Subcategory getNewSubcategory(AddSubcategoryDto subcategoryDto, Product product) {
        Subcategory subcategory = new Subcategory();
        subcategory.setProducts(Set.of(product));
        subcategory.setCategory(product.getCategory());
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
                .get()
                .getCost();
    }
}
