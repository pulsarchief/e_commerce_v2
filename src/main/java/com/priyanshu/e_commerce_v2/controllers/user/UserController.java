package com.priyanshu.e_commerce_v2.controllers.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.order.OrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.user.UserProfileUpdate;
import com.priyanshu.e_commerce_v2.dto.user.UserResponse;
import com.priyanshu.e_commerce_v2.security.CustomUserDetails;
import com.priyanshu.e_commerce_v2.service.OrderService;
import com.priyanshu.e_commerce_v2.service.UserService;
import com.priyanshu.e_commerce_v2.util.mappers.OrderMappers;
import com.priyanshu.e_commerce_v2.util.mappers.UserMappers;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMappers userMappers;
    private final OrderService orderService;
    private final OrderMappers orderMappers;

    // get current auth user details
    @GetMapping("")
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal CustomUserDetails userDetails) {

        List<OrderSummaryResponse> orderDto = orderService.findTop3ForUser(userDetails.getId()).stream()
                .map(x -> orderMappers.toOrderSummaryResponse(x)).toList();

        UserResponse userResponse = userMappers.toDto(userService.findUserById(userDetails.getId()),
                orderDto);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    // current auth user update their details
    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUserDetails(@AuthenticationPrincipal CustomUserDetails userDetails,
            UserProfileUpdate newDetails) {

        List<OrderSummaryResponse> orderDto = orderService.findTop3ForUser(userDetails.getId()).stream()
                .map(x -> orderMappers.toOrderSummaryResponse(x)).toList();

        UserResponse userResponse = userMappers.toDto(userService.updateUser(userDetails.getId(), newDetails),
                orderDto);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    // current auth user deletes their data (archived)
    @DeleteMapping("")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {

        userService.deleteUser(userDetails.getId());

        return new ResponseEntity<String>("User Deleted , Please Destroy Jwt Token", HttpStatus.OK);
    }

}
