package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FilteringBlockServiceImpl implements FilteringBlockService {

    private final ProductListForView productListForView;

    private final SubcategoryService subcategoryService;

    private final PriceFilter priceFilter;

    private List<Product> productListByCategory;

    private final List<CheckboxForSubcategory> checkboxes = new ArrayList<>();

    private List<Subcategory> subcategories;

    private List<BlockOfCriteria> blocksOfCriteria;

    public FilteringBlockServiceImpl(ProductListForView productListForView, SubcategoryService subcategoryService, PriceFilter priceFilter) {
        this.productListForView = productListForView;
        this.subcategoryService = subcategoryService;
        this.priceFilter = priceFilter;
    }

    /**
     * The most important method in this class.
     * This method sources the global state for the category by {@categoryId}, clears the state
     * of the objects involved in generating the page state, and assigns them new values relevant
     * to the given category.
     *
     * @param categoryId
     */

    public void refreshStateCategoryPageByCategoryId(String categoryId) {
        clearState();
        subcategories = subcategoryService.getAllSubcategoryByCategoryId(categoryId);
        productListForView.buildByCategoryId(categoryId);
        setProductListByCategory();
        addOtherSubcategory();
        buildPriceFilter(categoryId);
        blocksOfCriteria = buildBlockOfFilters();

    }

    /**
     * @param checkbox
     */
    public void refreshStateCategoryPageByCheckBox(CheckboxForSubcategory checkbox) {
        Objects.requireNonNull(checkbox);
        actionForCheckbox(checkbox);
        subcategories = subcategoryService.getAllSubcategoryByCategoryId(checkbox.getCategoryId());
        addOtherSubcategory();
        checkboxes.forEach(this::updateSubcategoriesByCheckbox);
        subcategories.forEach(s -> s.setProducts(filteringProductsByPriceFilter(s)));
        subcategories.forEach(s -> s.setProductCount(s.getProducts().size()));
        subcategories.stream().filter(s -> s.getProductCount() == 0)
                .forEach(s -> s.setClickable(false));
        blocksOfCriteria = buildBlockOfFilters();
        buildProductListForView();
    }

    private void actionForCheckbox(CheckboxForSubcategory checkbox) {
        if (checkbox.getActive()) {
            checkboxes.add(checkbox);
        } else {
            checkboxes.remove(checkbox);
        }
    }

    public void refreshPriceFilter(PriceFilter priceFilter) {
        Objects.requireNonNull(priceFilter);
        this.priceFilter.setMinPrice(priceFilter.getMinPrice());
        this.priceFilter.setMaxPrice(priceFilter.getMaxPrice());
        this.priceFilter.setCategoryId(priceFilter.getCategoryId());
    }

    private void buildPriceFilter(String categoryId) {
        double min = getMinPriceFromCategory();
        double max = getMaxPriceFromCategory();
        setValuesForPriceFilter(categoryId, (double) min, (double) max);
    }

    private double getMaxPriceFromCategory() {
        return productListByCategory
                .stream()
                .mapToDouble(this::mapToMinPrice)
                .max().getAsDouble();
    }

    private double getMinPriceFromCategory() {
        return productListByCategory
                .stream()
                .mapToDouble(this::mapToMinPrice)
                .min().getAsDouble();
    }

    private void setValuesForPriceFilter(String categoryId, double min, double max) {
        priceFilter.setMinPrice(min);
        priceFilter.setMaxPrice(max);
        priceFilter.setCategoryId(categoryId);
    }

    public List<BlockOfCriteria> buildBlockOfFilters() {
        Set<String> titles = getTitleFromSubcategory(subcategories);
        return buildBlockOfCriteria(subcategories, titles);
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
        return subcategory.getProducts().stream()
                .filter(p -> mapToMinPrice(p) >= priceFilter.getMinPrice())
                .filter(p -> mapToMinPrice(p) <= priceFilter.getMaxPrice())
                .collect(Collectors.toSet());
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

    private void buildProductListForView() {
        Set<Product> products = new TreeSet<>();
        addValidProductsIntoSet(products);
        productListForView.setProductList(new ArrayList<>(products));
    }

    private void addValidProductsIntoSet(Set<Product> products) {
        subcategories
                .stream()
                .filter(s -> s.getProductCount() > 0)
                .forEach(s -> products.addAll(s.getProducts()));
    }

    private List<BlockOfCriteria> buildBlockOfCriteria(List<Subcategory> subcategories, Set<String> titles) {
        return titles
                .stream()
                .map(t -> BlockOfCriteria.create(t, getSubcategoriesByTitle(subcategories, t)))
                .collect(Collectors.toList());
    }

    private List<Subcategory> getSubcategoriesByTitle(List<Subcategory> subcategories, String t) {
        return subcategories
                .stream()
                .filter(s -> s.getTitle().equals(t))
                .collect(Collectors.toList());
    }

    private Set<String> getTitleFromSubcategory(List<Subcategory> subcategories) {
        return subcategories
                .stream()
                .map(Subcategory::getTitle)
                .collect(Collectors.toSet());
    }

    private Subcategory createPromotionalProductsSubcategory() {
        Subcategory promotionalProducts = new Subcategory();
        Set<Product> products = getProductsByPredicate(s -> s.getDiscountPercent() > 0);

        promotionalProducts.setId("promotional_products");
        promotionalProducts.setName("Акційні товари");
        promotionalProducts.setTitle("Інші");
        promotionalProducts.setCategory(subcategories.stream().findFirst().get().getCategory());
        promotionalProducts.setProducts(products);
        promotionalProducts.setProductCount(products.size());
        return promotionalProducts;
    }

    private Subcategory createGiftSetSubcategory() {
        Subcategory giftSet = new Subcategory();
        Set<Product> products = getProductsByPredicate(Product::isGiftSet);

        giftSet.setId("gift_set");
        giftSet.setName("Подарункові набори");
        giftSet.setTitle("Інші");
        giftSet.setCategory(subcategories.stream().findFirst().get().getCategory());
        giftSet.setProducts(products);
        giftSet.setProductCount(products.size());
        return giftSet;
    }

    private Set<Product> getProductsByPredicate(Predicate<Product> predicate) {
        return productListByCategory
                .stream()
                .filter(predicate)
                .collect(Collectors.toSet());
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
        subcategories.addAll(subcategories.size(), createCountrySubcategories());
    }

    private List<Subcategory> createCountrySubcategories() {
        Set<String> countries = extractCountryNamesFromProducts();
        return countries
                .stream()
                .map(c -> createCountryProducerSubcategory(c, c)).toList();
    }

    private Set<String> extractCountryNamesFromProducts() {
        return productListByCategory
                .stream()
                .map(Product::getCountryProducer)
                .collect(Collectors.toSet());
    }


    private void setProductListByCategory() {
        productListByCategory = new ArrayList<>(productListForView.getProductList());
    }

    public List<BlockOfCriteria> getBlocksOfCriteria() {
        return blocksOfCriteria;
    }

    private void clearState() {
        productListForView.getProductList().clear();
        checkboxes.clear();
        subcategories = null;
        subcategories = null;
        blocksOfCriteria = null;
        priceFilter.setCategoryId(null);
        priceFilter.setMaxPrice(null);
        priceFilter.setMinPrice(null);
    }
}
