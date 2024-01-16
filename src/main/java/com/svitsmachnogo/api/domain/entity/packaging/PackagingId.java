package com.svitsmachnogo.api.domain.entity.packaging;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
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
