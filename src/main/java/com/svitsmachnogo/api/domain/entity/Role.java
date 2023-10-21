package com.svitsmachnogo.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns =@JoinColumn(name = "user_id"))
    private List<User> users;
}
