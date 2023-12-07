package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private UserProfile userProfile;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @Column(name = "customer_address")
    private String customerAddress;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "carts_packaging",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
                    @JoinColumn(name = "amount", referencedColumnName = "amount")})
    private List<Packaging> packagingList; // product list that contains in cards

}
