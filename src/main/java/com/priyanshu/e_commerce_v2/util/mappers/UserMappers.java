package com.priyanshu.e_commerce_v2.util.mappers;

import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.auth.registration.UserRegistrationRequest;
import com.priyanshu.e_commerce_v2.entity.user.Users;

@Component
public class UserMappers {

    public Users toUser(UserRegistrationRequest request) {
        Users user = new Users();

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());

        return user;
    }

}
