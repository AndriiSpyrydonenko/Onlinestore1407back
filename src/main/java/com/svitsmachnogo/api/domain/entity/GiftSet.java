package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;

import java.net.URL;
import java.util.Objects;

@Entity
@Table(name = "gift_sets")
public class GiftSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_set_id")
    private int id;

    @Column(name = "url_picture",nullable = false)
    private URL urlToPicture;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent = 0;

    public GiftSet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getUrlToPicture() {
        return urlToPicture;
    }

    public void setUrlToPicture(URL urlToPicture) {
        this.urlToPicture = urlToPicture;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiftSet giftSet = (GiftSet) o;

        if (id != giftSet.id) return false;
        if (discountPercent != giftSet.discountPercent) return false;
        return Objects.equals(urlToPicture, giftSet.urlToPicture);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (urlToPicture != null ? urlToPicture.hashCode() : 0);
        result = 31 * result + discountPercent;
        return result;
    }
}
