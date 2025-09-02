package com.priyanshu.e_commerce_v2.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.dto.auth.login.UserLoginRequest;
import com.priyanshu.e_commerce_v2.dto.auth.login.UserLoginResponse;
import com.priyanshu.e_commerce_v2.dto.auth.passwordReset.PasswordResetRequest;
import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationRequest;
import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationResponse;
import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.exception.PasswordMismatchException;
import com.priyanshu.e_commerce_v2.security.CustomUserDetails;
import com.priyanshu.e_commerce_v2.security.jwt.JwtService;
import com.priyanshu.e_commerce_v2.util.mappers.UserMappers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserMappers userMappers;

    private final PasswordEncoder passwordEncoder;

    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        Users user = userMappers.toUser(request);

        user = userService.addUser(user);
        UserRegistrationResponse response = new UserRegistrationResponse(
                "Registration Successful " + user.getUsername(), createJwt(new CustomUserDetails(user)));

        return response;
    }

    public String createJwt(UserDetails userDetails) {

        return jwtService.createJwtToken(userDetails);

    }

    public UserLoginResponse login(UserLoginRequest request) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String jwt = createJwt((CustomUserDetails) auth.getPrincipal());

        return new UserLoginResponse("Login Successful", jwt);
    }

    public void resetPassword(Long userId, PasswordResetRequest request) {

        Users user = userService.findUserById(userId);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Wrong Password Input");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userService.addUser(user);

    }

}
