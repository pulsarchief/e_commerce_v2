package com.priyanshu.e_commerce_v2.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationRequest;
import com.priyanshu.e_commerce_v2.dto.order.AdminOrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.order.OrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.user.AdminUserResponse;
import com.priyanshu.e_commerce_v2.dto.user.UserResponse;
import com.priyanshu.e_commerce_v2.entity.user.Users;

@Component
public class UserMappers {

    public Users toUser(UserRegistrationRequest request) {
        Users user = new Users();

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setName(request.getName());

        return user;
    }

    public UserResponse toDto(Users user, List<OrderSummaryResponse> orders) {

        UserResponse response = new UserResponse();

        response.setEmail(user.getEmail());
        response.setUserId(user.getUserId().toString());
        response.setUsername(user.getUsername());

        response.setName(user.getName());

        response.setOrders(orders);

        if (user.getUserImage() != null) {
            response.setUserImageUrl((user.getUserImage().getPath()));
        }

        return response;

    }

    public AdminUserResponse toAdminDto(Users user, List<AdminOrderSummaryResponse> orders) {
        AdminUserResponse response = new AdminUserResponse();

        response.setEmail(user.getEmail());
        response.setUserId(user.getUserId().toString());
        response.setUsername(user.getUsername());
        response.setDbId(user.getId());
        response.setRole(user.getRole());
        response.setEnabled(user.getEnabled());
        response.setName(user.getName());

        response.setOrders(orders);

        if (user.getUserImage() != null) {
            response.setUserImageUrl(user.getUserImage().getPath());
        }

        return response;

    }

}
