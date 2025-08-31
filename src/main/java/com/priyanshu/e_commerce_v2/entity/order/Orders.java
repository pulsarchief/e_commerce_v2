package com.priyanshu.e_commerce_v2.entity.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.priyanshu.e_commerce_v2.entity.orderItem.OrderItem;
import com.priyanshu.e_commerce_v2.entity.payment.Payment;
import com.priyanshu.e_commerce_v2.entity.user.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    OrderStatus status;

    BigDecimal price;

    Integer totalItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Users user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<OrderItem> products;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    Payment payment;

    @PrePersist
    @PreUpdate
    private void calculateTotals() {
        this.totalItems = products.stream().mapToInt(OrderItem::getQuantity).sum();
    }

    @PreUpdate
    @PrePersist
    private void calculatePrice() {
        this.price = products.stream().map(x -> x.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
