package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;

import java.util.List;

public interface FilteringBlockService {

    void refreshStateCategoryPageByCategoryId(String categoryId);

    void refreshStateCategoryPageByCheckBox(CheckboxForSubcategory checkbox);

    List<BlockOfCriteria> getBlocksOfCriteria();

    void refreshPriceFilter(PriceFilter priceFilter);
}
