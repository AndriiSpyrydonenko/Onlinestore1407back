package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class PackagingId implements Serializable {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "amount")
    private Integer amount;

}
