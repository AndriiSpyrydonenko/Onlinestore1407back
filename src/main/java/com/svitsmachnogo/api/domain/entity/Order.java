package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;

    @Column(name = "order_comment")
    private String comment;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_surname", nullable = false)
    private String customerSurname;

    @Column(name = "customer_phone_number", nullable = false)
    private String customerPhoneNumber;

    @Column(name = "customer_address", nullable = false)
    private String customerAddress;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;

    @Column(name = "pay_type", nullable = false)
    private String payType; // todo: change to enum. learning saving enum to db in correct view

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_packaging",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
                    @JoinColumn(name = "amount", referencedColumnName = "amount")})
    private List<Packaging> packagingList;
}
