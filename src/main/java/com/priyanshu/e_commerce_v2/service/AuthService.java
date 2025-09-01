package com.priyanshu.e_commerce_v2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.security.CustomUserDetails;
import com.priyanshu.e_commerce_v2.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final JwtService jwtService;

    public Users registerUser(Users user) {

        return userService.saveUser(user);

    }

    public String createJWT(Users user) {

        UserDetails userDetails = new CustomUserDetails(user);

        return jwtService.createJwtToken(userDetails);
    }

}
