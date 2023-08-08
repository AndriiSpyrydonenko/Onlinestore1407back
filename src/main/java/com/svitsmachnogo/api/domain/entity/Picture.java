package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;

import java.net.URL;
import java.util.Objects;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private int id;

    @Column(name = "url_path")
    private URL urlPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Picture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(URL urlPath) {
        this.urlPath = urlPath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (id != picture.id) return false;
        if (!Objects.equals(urlPath, picture.urlPath)) return false;
        return Objects.equals(product, picture.product);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (urlPath != null ? urlPath.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }


}
