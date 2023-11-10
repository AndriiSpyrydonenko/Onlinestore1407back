package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.component.CheckboxForSubcategory;
import com.svitsmachnogo.api.component.PriceFilter;

import java.util.List;

public interface FilteringBlockService {

    void buildDefaultFilteringBlockByCategoryId(String categoryId);

    void buildFilteringBlockByFilterAspects(String categoryId,
                                            List<CheckboxForSubcategory> checkboxes ,
                                            PriceFilter priceFilter);

    List<BlockOfCriteria> getBlocksOfCriteria();

    void clearState();

}
