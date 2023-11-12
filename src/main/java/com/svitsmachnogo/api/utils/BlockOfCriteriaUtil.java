package com.svitsmachnogo.api.utils;

import com.svitsmachnogo.api.component.BlockOfCriteria;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BlockOfCriteriaUtil {
    public  List<BlockOfCriteria> buildBlockOfFilters(List<Subcategory> subcategories) {
        Set<String> titles = getTitleFromSubcategory(subcategories);
        return buildBlockOfCriteria(subcategories, titles);
    }

    private List<BlockOfCriteria> buildBlockOfCriteria(List<Subcategory> subcategories, Set<String> titles) {
        return titles
                .stream()
                .map(t -> BlockOfCriteria.of(t, getSubcategoriesByTitle(subcategories, t)))
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

}
