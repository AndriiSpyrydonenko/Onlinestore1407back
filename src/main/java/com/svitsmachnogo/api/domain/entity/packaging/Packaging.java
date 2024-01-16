package com.svitsmachnogo.api.domain.entity.packaging;

import com.svitsmachnogo.api.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "packaging")
public class Packaging {

    @EmbeddedId
    private PackagingId id;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Packaging packaging = (Packaging) o;
        return id != null && Objects.equals(id, packaging.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


