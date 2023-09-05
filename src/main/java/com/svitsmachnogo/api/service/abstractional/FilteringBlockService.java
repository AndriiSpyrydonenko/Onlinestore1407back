package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.exceptions.WrongPriceFilterException;

import java.util.List;

public interface FilteringBlockService {

     void refreshStateCategoryPageByCategoryId(String categoryId) throws WrongPriceFilterException;

     void refreshStateCategoryPageByCheckBox(CheckboxForSubcategory checkbox);

     List<BlockOfCriteria> getBlocksOfCriteria();

     void refreshPriceFilter(PriceFilter priceFilter);
}
