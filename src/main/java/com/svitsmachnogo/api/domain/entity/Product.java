package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Comparable<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "article", nullable = false)
    private int article;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "big_description", nullable = false)
    private String bigDescription;

    @Column(name = "nutritional_value", nullable = true)
    private String nutritionalValue;

    @Column(name = "usage_description", nullable = true)
    private String usage;

    @Column(name = "country_producer")
    private String countryProducer;

    @Column(name = "exist", nullable = false)
    private boolean exist = true;

    @Column(name = "is_gift_set", nullable = false)
    private boolean isGiftSet = false;

    @Column(name = "priority_score", nullable = false)
    private int priorityScore = 0;

    @Column(name = "rating")
    private double rating;

    @Column(name = "review_count", nullable = false)
    private int reviewCount;

    @Column(name = "number_of_orders", nullable = false)
    private int numberOfOrders;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent = 0;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "product")
    private List<Picture> pictures;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "min_price", nullable = false)
    private double minPrice = 0.0;

    @Column(name = "quantity", nullable = false)
    private int quantity ;


    @ElementCollection
    @CollectionTable(name = "packaging", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "amount")
    @Column(name = "cost")
    private Map<Integer, Double> packaging;

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}