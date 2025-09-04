package com.priyanshu.e_commerce_v2.controllers.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.order.AdminOrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.user.AdminUserResponse;
import com.priyanshu.e_commerce_v2.dto.user.UserProfileUpdate;
import com.priyanshu.e_commerce_v2.service.OrderService;
import com.priyanshu.e_commerce_v2.service.UserService;
import com.priyanshu.e_commerce_v2.util.mappers.OrderMappers;
import com.priyanshu.e_commerce_v2.util.mappers.UserMappers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserMappers userMappers;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderMappers orderMappers;

    // getting any user details by id
    @GetMapping("/{userId}")
    public ResponseEntity<AdminUserResponse> getUser(@PathVariable Long userId) {

        List<AdminOrderSummaryResponse> orderDto = orderService
                .findTop3ForUser(userId)
                .stream()
                .map(x -> orderMappers.toAdminOrderSummaryResponse(x)).toList();

        AdminUserResponse adminUserResponse = userMappers.toAdminDto(userService.findUserById(userId), orderDto);

        return new ResponseEntity<AdminUserResponse>(adminUserResponse, HttpStatus.OK);
    }

    // updating user details by id
    @PutMapping("/update/{userId}")
    public ResponseEntity<AdminUserResponse> updateUserDetails(@PathVariable Long userId,
            @RequestBody @Valid UserProfileUpdate newDetails) {
                
        List<AdminOrderSummaryResponse> orderDto = orderService.findTop3ForUser(userId).stream()
                .map(x -> orderMappers.toAdminOrderSummaryResponse(x)).toList();

        AdminUserResponse adminUserResponse = userMappers.toAdminDto(userService.updateUser(userId, newDetails),
                orderDto);

        return new ResponseEntity<>(adminUserResponse, HttpStatus.OK);
    }

    // deleting a user by id
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {

        userService.deleteUser(userId);

        return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.OK);
    }

}
