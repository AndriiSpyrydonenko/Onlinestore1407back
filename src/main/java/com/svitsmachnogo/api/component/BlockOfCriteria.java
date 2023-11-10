package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.entity.Subcategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Is a block of subcategories grouped by title
 *
 * @author Vanya Demydenko
 */
@Component
@Getter
@Setter
public class BlockOfCriteria {

    private String title;

    List<Subcategory> subcategories;

    private BlockOfCriteria() {
    }

    public static BlockOfCriteria create(String title, List<Subcategory> subcategories){
        BlockOfCriteria block = new BlockOfCriteria();
        block.title = title;
        block.subcategories = subcategories;
        return block;
    }

}
