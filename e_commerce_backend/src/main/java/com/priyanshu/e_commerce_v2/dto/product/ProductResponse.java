package com.priyanshu.e_commerce_v2.dto.product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {

    Long productId;

    String name;

    String description;

    String availability;

    BigDecimal price;

}
