package com.priyanshu.e_commerce_v2.entity.orderItem;

import java.math.BigDecimal;

import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.entity.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Orders order;

    BigDecimal pricePerUnit;

    int quantity;

    BigDecimal totalPrice;

    @PrePersist
    @PreUpdate
    private void totalPrice() {
        this.totalPrice = BigDecimal.valueOf(quantity).multiply(pricePerUnit);
    }
}
