package com.priyanshu.e_commerce_v2.service;

import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.exception.UserNotFoundException;
import com.priyanshu.e_commerce_v2.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users addUser(Users user) {

        user = userRepository.save(user);

        return user;
    }

    public Users findUserById(Long userId) {

        Users user = userRepository.findWithDetailsById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        return user;
    }

}
