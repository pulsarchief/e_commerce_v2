package com.priyanshu.e_commerce_v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Users saveUser(Users user) {

        user = userRepository.save(user);

        return user;
    }

}
