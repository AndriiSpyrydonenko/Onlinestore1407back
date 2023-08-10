package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id")
    private String id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "fill_picture_url", unique = true, nullable = false)
    private String fillPictureUrl;

    @Column(name = "hover_picture_url", unique = true, nullable = false)
    private String hoverPictureUrl;


    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "category")
    private List<Product> products;

    public Category() {
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

    public String getFillPictureUrl() {
        return fillPictureUrl;
    }

    public void setFillPictureUrl(String fillPictureUrl) {
        this.fillPictureUrl = fillPictureUrl;
    }

    public String getHoverPictureUrl() {
        return hoverPictureUrl;
    }

    public void setHoverPictureUrl(String hoverPictureUrl) {
        this.hoverPictureUrl = hoverPictureUrl;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!Objects.equals(id, category.id)) return false;
        if (!Objects.equals(name, category.name)) return false;
        if (!Objects.equals(fillPictureUrl, category.fillPictureUrl))
            return false;
        return Objects.equals(hoverPictureUrl, category.hoverPictureUrl);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fillPictureUrl != null ? fillPictureUrl.hashCode() : 0);
        result = 31 * result + (hoverPictureUrl != null ? hoverPictureUrl.hashCode() : 0);
        return result;
    }
}
