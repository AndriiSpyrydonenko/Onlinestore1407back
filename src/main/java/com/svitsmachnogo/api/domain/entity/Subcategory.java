package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@EqualsAndHashCode
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
}
