package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user")
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;

    public User(){
        UserProfile userProfile = new UserProfile();
        Cart cart = new Cart();
        userProfile.setUser(this);
        cart.setUserProfile(userProfile);
        userProfile.setCart(cart);
        this.userProfile = userProfile;
    }

}
