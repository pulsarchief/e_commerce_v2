package com.priyanshu.e_commerce_v2.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.auth.login.UserLoginRequest;
import com.priyanshu.e_commerce_v2.dto.auth.login.UserLoginResponse;
import com.priyanshu.e_commerce_v2.dto.auth.passwordReset.PasswordResetRequest;
import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationRequest;
import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationResponse;
import com.priyanshu.e_commerce_v2.security.CustomUserDetails;
import com.priyanshu.e_commerce_v2.service.AuthService;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody @Valid UserRegistrationRequest request) {

        UserRegistrationResponse response = authService.registerUser(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest request) {

        UserLoginResponse response = authService.login(request);

        return new ResponseEntity<UserLoginResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> passwordReset(@AuthenticationPrincipal CustomUserDetails user,
            @RequestBody PasswordResetRequest request) {

        authService.resetPassword(user.getId(), request);

        return new ResponseEntity<String>("Password Reset Successful", HttpStatus.OK);
    }

}
