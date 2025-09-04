package com.priyanshu.e_commerce_v2.dto.user;

import java.util.List;

import com.priyanshu.e_commerce_v2.dto.order.OrderSummaryResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    private String userId;

    private String username;

    private String email;

    private String name;

    private String userImageUrl;

    private List<OrderSummaryResponse> orders;
}
