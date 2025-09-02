package com.priyanshu.e_commerce_v2.entity.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.priyanshu.e_commerce_v2.entity.order.Orders;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, updatable = false)
    UUID userId = UUID.randomUUID();

    @Column(unique = true, updatable = false)
    String username;

    @Column(unique = true)
    String email;

    String password;

    @Enumerated(value = EnumType.STRING)
    UserRole role = UserRole.USER;

    Boolean archived = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    List<Orders> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    UserImageRecord userImage = null;
}
