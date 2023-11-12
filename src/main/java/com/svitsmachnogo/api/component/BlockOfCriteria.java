package com.svitsmachnogo.api.component;

import com.svitsmachnogo.api.domain.entity.Subcategory;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Is a block of subcategories grouped by title
 *
 * @author Vanya Demydenko
 */
@Component
@Data
public class BlockOfCriteria {

    private String title;

    List<Subcategory> subcategories;

    private BlockOfCriteria() {
    }

    public static BlockOfCriteria of(String title, List<Subcategory> subcategories) {
        BlockOfCriteria block = new BlockOfCriteria();
        block.title = title;
        block.subcategories = subcategories;
        return block;
    }

}
