package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "packaging")
public class Packaging {

    @EmbeddedId
    private PackagingId id;

    @Column(name = "cost")
    private Double cost;
}


