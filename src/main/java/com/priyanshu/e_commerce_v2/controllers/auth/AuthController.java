package com.priyanshu.e_commerce_v2.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationRequest;
import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationResponse;
import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.service.AuthService;
import com.priyanshu.e_commerce_v2.util.mappers.UserMappers;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMappers userMappers;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest request) {

        Users user = userMappers.toUser(request);

        Users savedUser = authService.registerUser(user);

        UserRegistrationResponse response = new UserRegistrationResponse();
        String jwtToken = authService.createJWT(savedUser);

        response.setMessage("Registration Successful Username:" + savedUser.getUsername());
        response.setJwtToken(jwtToken);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
