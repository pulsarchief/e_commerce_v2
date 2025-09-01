package com.priyanshu.e_commerce_v2.entity.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.priyanshu.e_commerce_v2.entity.orderItem.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, updatable = false)
    UUID productId = UUID.randomUUID();

    String name;

    String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<OrderItem> orderItems;

    Boolean active = true;

    BigDecimal price;

    Integer stock;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    ProductImageRecord productImage = null;
}
