package com.svitsmachnogo.api.component;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class CheckboxForSubcategory {

    private String categoryId;

    private String subcategoryId;

}
