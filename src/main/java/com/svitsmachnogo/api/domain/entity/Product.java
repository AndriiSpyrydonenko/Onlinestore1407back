package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Column(name = "description", nullable = false)
    private String description;

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
            mappedBy = "product")
    private List<Picture> pictures;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "min_price", nullable = false)
    private double minPrice = 0.0;

    @ElementCollection
    @CollectionTable(name = "packaging", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "amount")
    @Column(name = "cost")
    private Map<Integer, Double> packaging;

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.id, o.id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isGiftSet() {
        return isGiftSet;
    }

    public void setGiftSet(boolean giftSet) {
        isGiftSet = giftSet;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Map<Integer, Double> getPackaging() {
        return packaging;
    }

    public void setPackaging(Map<Integer, Double> packaging) {
        this.packaging = packaging;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (article != product.article) return false;
        if (exist != product.exist) return false;
        if (isGiftSet != product.isGiftSet) return false;
        if (priorityScore != product.priorityScore) return false;
        if (Double.compare(product.rating, rating) != 0) return false;
        if (reviewCount != product.reviewCount) return false;
        if (numberOfOrders != product.numberOfOrders) return false;
        if (discountPercent != product.discountPercent) return false;
        if (Double.compare(product.minPrice, minPrice) != 0) return false;
        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(category, product.category)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(description, product.description)) return false;
        if (!Objects.equals(countryProducer, product.countryProducer))
            return false;
        if (!Objects.equals(createDate, product.createDate)) return false;
        if (!Objects.equals(pictures, product.pictures)) return false;
        if (!Objects.equals(unit, product.unit)) return false;
        return Objects.equals(packaging, product.packaging);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + article;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (countryProducer != null ? countryProducer.hashCode() : 0);
        result = 31 * result + (exist ? 1 : 0);
        result = 31 * result + (isGiftSet ? 1 : 0);
        result = 31 * result + priorityScore;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + reviewCount;
        result = 31 * result + numberOfOrders;
        result = 31 * result + discountPercent;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        temp = Double.doubleToLongBits(minPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (packaging != null ? packaging.hashCode() : 0);
        return result;
    }
}