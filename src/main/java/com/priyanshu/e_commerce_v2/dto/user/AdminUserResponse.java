package com.priyanshu.e_commerce_v2.dto.user;

import java.util.List;

import com.priyanshu.e_commerce_v2.dto.order.AdminOrderSummaryResponse;
import com.priyanshu.e_commerce_v2.entity.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminUserResponse {

    Long dbId;

    String userId;

    String username;

    String email;

    String name;

    UserRole role;

    Boolean enabled;

    String userImageUrl;

    private List<AdminOrderSummaryResponse> orders;

}
