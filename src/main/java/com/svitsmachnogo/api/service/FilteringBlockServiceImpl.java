package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.component.*;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import com.svitsmachnogo.api.service.abstractional.ProductService;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import com.svitsmachnogo.api.utils.BlockOfCriteriaUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * Implementation of the {@link FilteringBlockService} interface.
 * This class receives a list of subcategories from the {@link SubcategoryService}
 * and generates a list of filters based on these categories.
 * It also generates a list of products based on these filters.
 *
 * @author Vanya Demydenko
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class FilteringBlockServiceImpl implements FilteringBlockService {

    private final ProductListForView productListForView;

    private final SubcategoryService subcategoryService;

    private final BlockOfCriteriaUtil blockOfCriteriaUtil;

    private List<Product> productListByCategory;

    private List<Subcategory> subcategories;

    private List<BlockOfCriteria> blocksOfCriteria;




    /**
     * Builds a default filtering block for a specified category based on its ID. This method is called to generate the initial page
     * corresponding to the given category ID.
     *
     * @author Vanya Demydenko
     * @param categoryId The ID of the category for which the default filtering block is generated.
     */

    public void buildDefaultFilteringBlockByCategoryId(String categoryId) {
        clearState();
        setSubcategories(subcategoryService.getAllSubcategoryByCategoryId(categoryId));
        productListForView.buildByCategoryId(categoryId);
        setProductListByCategory();
        addOtherSubcategory();
        setBlocksOfCriteria(blockOfCriteriaUtil.buildBlockOfFilters(subcategories));
    }

    /**
     * Builds a filtering block and filters products based on the specified filter aspects.
     *
     * @author Vanya Demydenko
     * @param categoryId   The ID of the category of products for which lists are being loaded.
     * @param checkboxes   A list of checkboxes representing subcategories
     *                     for which filtering is to be applied.
     * @param priceFilter  The price filter specifying the price range for filtering products.
     */
    public void buildFilteringBlockByFilterAspects(String categoryId,
                                                   List<CheckboxForSubcategory> checkboxes,
                                                   PriceFilter priceFilter) {
        productListForView.buildByCategoryId(categoryId);
        setProductListByCategory();
        subcategories = subcategoryService.getAllSubcategoryByCategoryId(categoryId);
        addOtherSubcategory();
        checkboxes.forEach(this::updateSubcategoriesByCheckbox);
        subcategories.forEach(s -> s.setProducts(filteringProductsByPriceFilter(s , priceFilter)));
        subcategories.forEach(s -> s.setProductCount(s.getProducts().size()));
        subcategories.stream().filter(s -> s.getProductCount() == 0)
                .forEach(s -> s.setClickable(false));
        blocksOfCriteria = blockOfCriteriaUtil.buildBlockOfFilters(subcategories);
        buildProductListForView();
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

    private Set<Product> filteringProductsByPriceFilter(Subcategory subcategory , PriceFilter priceFilter) {
        return subcategory.getProducts().stream()
                .filter(p -> mapToMinPrice(p) >= priceFilter.getMinPrice())
                .filter(p -> mapToMinPrice(p) <= priceFilter.getMaxPrice())
                .collect(Collectors.toSet());
    }

    private void updateSubcategoriesByCheckbox(CheckboxForSubcategory checkbox) {
        Subcategory subcategory = subcategories
                .stream()
                .filter(s -> s.getId().equals(checkbox.getSubcategoryId()))
                .peek(s -> s.setActive(true))
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

    /**
     * Returns the actual filters block
     *
     * @return {@link BlockOfCriteria}
     */
    public List<BlockOfCriteria> getBlocksOfCriteria() {
        return blocksOfCriteria;
    }

    @Override
    public void clearState() {
        productListForView.getProductList().clear();
        subcategories = null;
        blocksOfCriteria = null;
    }
}
