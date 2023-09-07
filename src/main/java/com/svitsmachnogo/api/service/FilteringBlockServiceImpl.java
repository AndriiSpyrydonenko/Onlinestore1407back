package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilteringBlockServiceImpl implements FilteringBlockService {

    @Autowired
    ProductListForView productListForView;

    @Autowired
    SubcategoryService subcategoryService;

    @Autowired
    PriceFilter priceFilter;

    private List<Product> productListByCategory;

    private final List<CheckboxForSubcategory> checkboxes = new ArrayList<>();

    private List<Subcategory> subcategories;

    private List<BlockOfCriteria> blocksOfCriteria;


    public void refreshStateCategoryPageByCategoryId(String categoryId) {
        clearState();
        subcategories = subcategoryService.getAllSubcategoryByCategoryId(categoryId);
        productListForView.buildByCategoryId(categoryId);
        setProductListByCategory();
        addOtherSubcategory();
        buildPriceFilterState(categoryId);
        blocksOfCriteria = buildBlockForCategoryPage();

    }

    public void refreshStateCategoryPageByCheckBox(CheckboxForSubcategory checkbox) {
        Objects.requireNonNull(checkbox);
        if (checkbox.getActive()) {
            checkboxes.add(checkbox);
        } else {
            checkboxes.remove(checkbox);
        }
        subcategories = subcategoryService.getAllSubcategoryByCategoryId(checkbox.getCategoryId());
        addOtherSubcategory();
        checkboxes.forEach(this::updateSubcategoriesByCheckbox);
        subcategories.forEach(s -> s.setProducts(filteringProductsByPriceFilter(s)));
        subcategories.forEach(s -> s.setProductCount(s.getProducts().size()));
        subcategories.stream().filter(s -> s.getProductCount() == 0)
                .forEach(s -> s.setClickable(false));
        blocksOfCriteria = buildBlockForCategoryPage();
        buildProductListForView();
    }

    private void buildPriceFilterState(String categoryId) {
        double min = productListByCategory
                .stream()
                .mapToDouble(this::mapToMinPrice)
                .min().getAsDouble();

        double max = productListByCategory
                .stream()
                .mapToDouble(this::mapToMinPrice)
                .max().getAsDouble();

        priceFilter.setMinPrice((double) min);
        priceFilter.setMaxPrice((double) max);
        priceFilter.setCategoryId(categoryId);
    }

    public void refreshPriceFilter(PriceFilter priceFilter) {
        Objects.requireNonNull(priceFilter);
        this.priceFilter.setMinPrice(priceFilter.getMinPrice());
        this.priceFilter.setMaxPrice(priceFilter.getMaxPrice());
        this.priceFilter.setCategoryId(priceFilter.getCategoryId());
    }

    private double mapToMinPrice(Product product) {
        return product
                .getPackaging()
                .get(product
                        .getPackaging()
                        .keySet()
                        .stream()
                        .min(Integer::compareTo)
                        .get());
    }

    private Set<Product> filteringProductsByPriceFilter(Subcategory subcategory) {
        Set<Product> productSet = new TreeSet<>();
//        products.removeIf(p -> mapToMinPrice(p) >= priceFilter.getMinPrice());
//        products.removeIf(p -> mapToMinPrice(p) <= priceFilter.getMaxPrice());
        productSet = subcategory.getProducts().stream()
                .filter(p -> mapToMinPrice(p) >= priceFilter.getMinPrice())
                .filter(p -> mapToMinPrice(p) <= priceFilter.getMaxPrice())
                .collect(Collectors.toSet());
        return productSet;
    }

    private void updateSubcategoriesByCheckbox(CheckboxForSubcategory checkbox) {
        Subcategory subcategory = subcategories
                .stream()
                .filter(s -> s.getId().equals(checkbox.getSubcategoryId()))
                .peek(s -> s.setActive(checkbox.getActive()))
                .findFirst()
                .get();
        subcategories.forEach(s -> removeProductFromSubcategory(s, subcategory));
    }

    private void removeProductFromSubcategory(Subcategory target, Subcategory example) {
        target.getProducts().removeIf(p -> example.getProducts().stream().noneMatch(e -> e.getId().equals(p.getId())));
    }

    public List<BlockOfCriteria> buildBlockForCategoryPage() {
        Set<String> titles = getTitleFromSubcategory(subcategories);
        return buildBlockOfCriteria(subcategories, titles);
    }

    private void buildProductListForView() {
        Set<Product> products = new TreeSet<>();
        subcategories
                .stream()
                .filter(s -> s.getProductCount() > 0)
                .forEach(s -> s.getProducts().forEach(products::add));

        productListForView.setProductList(new ArrayList<>(products));
    }

    private static List<BlockOfCriteria> buildBlockOfCriteria(List<Subcategory> subcategories, Set<String> titles) {
        return titles
                .stream()
                .map(t -> BlockOfCriteria.create(t, getSubcategoriesByTitle(subcategories, t)))
                .collect(Collectors.toList());
    }

    private static List<Subcategory> getSubcategoriesByTitle(List<Subcategory> subcategories, String t) {
        return subcategories
                .stream()
                .filter(s -> s.getTitle().equals(t))
                .collect(Collectors.toList());
    }

    private static Set<String> getTitleFromSubcategory(List<Subcategory> subcategories) {
        return subcategories
                .stream()
                .map(Subcategory::getTitle)
                .collect(Collectors.toSet());
    }

    private void clearState() {
        productListForView.getProductList().clear();
        checkboxes.clear();
        subcategories = null;
        blocksOfCriteria = null;
        priceFilter.setCategoryId(null);
        priceFilter.setMaxPrice(null);
        priceFilter.setMinPrice(null);
    }

    private Subcategory createPromotionalProductsSubcategory() {
        Subcategory promotionalProducts = new Subcategory();
        promotionalProducts.setId("promotional_products");
        promotionalProducts.setName("Акційні товари");
        promotionalProducts.setTitle("Інші");
        promotionalProducts.setCategory(subcategories.stream().findFirst().get().getCategory());
        Set<Product> products = productListByCategory
                .stream()
                .filter(s -> s.getDiscountPercent() > 0)
                .collect(Collectors.toSet());
        promotionalProducts.setProducts(products);
        promotionalProducts.setProductCount(products.size());
        return promotionalProducts;
    }

    private Subcategory createGiftSetSubcategory() {
        Subcategory giftSet = new Subcategory();
        giftSet.setId("gift_set");
        giftSet.setName("Подарункові набори");
        giftSet.setTitle("Інші");
        giftSet.setCategory(subcategories.stream().findFirst().get().getCategory());
        Set<Product> products = productListByCategory
                .stream()
                .filter(Product::isGiftSet)
                .collect(Collectors.toSet());
        giftSet.setProducts(products);
        giftSet.setProductCount(products.size());
        return giftSet;
    }

    private Subcategory createCountryProducerSubcategory(String countryProducerName, String countryProducerId) {
        Subcategory countryProducer = new Subcategory();
        countryProducer.setId(countryProducerId);
        countryProducer.setName(countryProducerName);
        countryProducer.setTitle("Країна виробник");
        countryProducer.setCategory(subcategories.get(0).getCategory());
        Set<Product> products = productListByCategory
                .stream()
                .filter(p -> p.getCountryProducer().equals(countryProducerName))
                .collect(Collectors.toSet());

        countryProducer.setProducts(products);
        countryProducer.setProductCount(products.size());
        return countryProducer;
    }

    private void addOtherSubcategory() {
        subcategories.add(0, createPromotionalProductsSubcategory());
        subcategories.add(1, createGiftSetSubcategory());
        Set<String> countries = productListByCategory
                .stream()
                .map(Product::getCountryProducer)
                .collect(Collectors.toSet());
        List<Subcategory> countriesSubcategory = countries
                .stream()
                .map(c -> createCountryProducerSubcategory(c, c)).toList();
        subcategories.addAll(subcategories.size(), countriesSubcategory);
    }


    private void setProductListByCategory() {
        productListByCategory = new ArrayList<>(productListForView.getProductList());
    }

    public ProductListForView getProductListForView() {
        return productListForView;
    }

    public void setProductListForView(ProductListForView productListForView) {
        this.productListForView = productListForView;
    }

    public List<BlockOfCriteria> getBlocksOfCriteria() {
        return blocksOfCriteria;
    }

    public void setBlocksOfCriteria(List<BlockOfCriteria> blocksOfCriteria) {
        this.blocksOfCriteria = blocksOfCriteria;
    }
}
