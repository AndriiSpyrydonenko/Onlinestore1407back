package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "article",nullable = false)
    private int article;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "country_producer")
    private String countryProducer;

    @Column(name = "exist", nullable = false)
    private boolean exist = true;

    @Column(name = "priority_score", nullable = false)
    private int priorityScore = 0;

    @Column(name = "rating")
    private double rating;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent = 0;

    @Column(name = "create_date", nullable = false)
    private Timestamp create_date;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "product")
    private List<Picture> pictures;


    @ElementCollection
    @CollectionTable(name = "packaging" , joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "unit")
    @Column(name = "cost")
    private Map<String, Integer> packaging;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryProducer() {
        return countryProducer;
    }

    public void setCountryProducer(String countryProducer) {
        this.countryProducer = countryProducer;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Map<String, Integer> getPackaging() {
        return packaging;
    }

    public void setPackaging(Map<String, Integer> packaging) {
        this.packaging = packaging;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (article != product.article) return false;
        if (exist != product.exist) return false;
        if (priorityScore != product.priorityScore) return false;
        if (Double.compare(product.rating, rating) != 0) return false;
        if (reviewCount != product.reviewCount) return false;
        if (discountPercent != product.discountPercent) return false;
        if (!Objects.equals(category, product.category)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(description, product.description)) return false;
        if (!Objects.equals(countryProducer, product.countryProducer))
            return false;
        if (!Objects.equals(create_date, product.create_date)) return false;
        if (!Objects.equals(pictures, product.pictures)) return false;
        return Objects.equals(packaging, product.packaging);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + article;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (countryProducer != null ? countryProducer.hashCode() : 0);
        result = 31 * result + (exist ? 1 : 0);
        result = 31 * result + priorityScore;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + reviewCount;
        result = 31 * result + discountPercent;
        result = 31 * result + (create_date != null ? create_date.hashCode() : 0);
        result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
        result = 31 * result + (packaging != null ? packaging.hashCode() : 0);
        return result;
    }
}