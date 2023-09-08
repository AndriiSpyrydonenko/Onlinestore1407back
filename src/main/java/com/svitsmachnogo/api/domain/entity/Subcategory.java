package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a subcategory of products in the system.
 * Subcategories provide a way to further classify products
 * within a broader category.
 *
 * This class is an entity and is mapped to the corresponding
 * database table 'subcategories'.
 *
 * @author Vanya Demydenko
 */

@Entity
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @Column(name = "subcategory_id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Transient
    private int productCount;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_subcategories",
            joinColumns = @JoinColumn(name = "subcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new TreeSet<>();

    @Transient
    private boolean clickable = true;

    @Transient
    private boolean active = false;

    public Subcategory() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subcategory that = (Subcategory) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
