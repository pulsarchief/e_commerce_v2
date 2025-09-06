package com.priyanshu.e_commerce_v2.dto.product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminProductResponse {

    Long productId;

    String name;

    String description;

    Integer stock;

    BigDecimal price;

    Boolean active;
}
