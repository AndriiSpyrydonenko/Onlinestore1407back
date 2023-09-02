package com.svitsmachnogo.api.component;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CheckboxForSubcategory {

    private String categoryId;

    private String subcategoryId;

    private Boolean active;

    public CheckboxForSubcategory() {
    }

    public CheckboxForSubcategory(String categoryId, String subcategoryId, boolean active) {
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.active = active;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckboxForSubcategory that = (CheckboxForSubcategory) o;

        return Objects.equals(subcategoryId, that.subcategoryId);
    }

    @Override
    public int hashCode() {
        return subcategoryId != null ? subcategoryId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CheckboxForSubcategory{" +
                "categoryId='" + categoryId + '\'' +
                ", subcategoryId='" + subcategoryId + '\'' +
                ", active=" + active +
                '}';
    }
}
