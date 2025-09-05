package com.priyanshu.e_commerce_v2.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntry {

    @NotEmpty
    String name;

    String description;

    Integer stock;

    BigDecimal price;

    Boolean active;

}
