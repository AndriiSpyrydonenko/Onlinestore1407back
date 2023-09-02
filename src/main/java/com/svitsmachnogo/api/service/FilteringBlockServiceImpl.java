package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.ProductListForView;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.service.abstractional.FilteringBlockService;
import com.svitsmachnogo.api.service.abstractional.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilteringBlockServiceImpl implements FilteringBlockService {

    @Autowired
    ProductListForView productListForView;

    @Autowired
    SubcategoryService subcategoryService;

    private final List<CheckboxForSubcategory> checkboxes = new ArrayList<>();

    private  List<Subcategory> subcategories ;

    private List<BlockOfCriteria> blocksOfCriteria;



    public void refreshStateCategoryPageByCategoryId(String categoryId){
        clearState();
        subcategories = subcategoryService.getAllSubcategoryByCategoryId(categoryId);
        blocksOfCriteria = buildBlockForCategoryPage();
        buildProductListForView(categoryId);
    }

    public void refreshStateCategoryPageByCheckBox(CheckboxForSubcategory checkbox){
        if(checkbox.getActive()) {
            checkboxes.add(checkbox);
        }else {
            checkboxes.remove(checkbox);
        }
        System.out.println(checkboxes);
        subcategories = subcategoryService.getAllSubcategoryByCategoryId(checkbox.getCategoryId());
        checkboxes.forEach(this::updateSubcategoriesByCheckbox);
        subcategories.forEach(s -> s.setProductCount(s.getProducts().size()));
        subcategories.stream().filter(s -> s.getProductCount() == 0)
                .forEach(s -> s.setClickable(false));
        blocksOfCriteria = buildBlockForCategoryPage();
        buildProductListForView(checkbox.getCategoryId());
    }

    private void updateSubcategoriesByCheckbox(CheckboxForSubcategory checkbox){
        Subcategory subcategory = subcategories
                .stream()
                .filter(s -> s.getId().equals(checkbox.getSubcategoryId()))
                .peek(s -> s.setActive(checkbox.getActive()))
                .findFirst()
                .get();
        subcategories.forEach(s -> removeProductFromSubcategory(s , subcategory));
    }

    private void removeProductFromSubcategory(Subcategory target , Subcategory example  ){
        target.getProducts().removeIf(p -> !example.getProducts().contains(p));
    }

    public List<BlockOfCriteria> buildBlockForCategoryPage() {
        Set<String> titles = getTitleFromSubcategory(subcategories);
        return buildBlockOfCriteria(subcategories, titles);
    }

    private void buildProductListForView(String categoryId){
        if(checkboxes==null || checkboxes.isEmpty()){
            productListForView.buildByCategoryId(categoryId);
        }
        else{
            Set<Product> products = subcategories
                    .stream()
                    .filter(s -> s.getProductCount() > 0)
                    .max(Comparator.comparing(Subcategory::getProductCount))
                    .get()
                    .getProducts();

            productListForView.setProductList(new ArrayList<>(products));
        }
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

    private void clearState(){
        productListForView.getProductList().clear();
        checkboxes.clear();
        subcategories = null;
        blocksOfCriteria = null;
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
