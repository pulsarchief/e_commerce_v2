package com.priyanshu.e_commerce_v2.service;

import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.dto.user.AdminUserUpdate;
import com.priyanshu.e_commerce_v2.dto.user.UserProfileUpdate;
import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.exception.DuplicateCredentialsException;
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

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateCredentialsException("Username in use, Please use another one");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateCredentialsException("Email already registered with another account.");
        }

        user = userRepository.save(user);

        return user;
    }

    public Users findUserById(Long userId) {

        Users user = userRepository.findWithDetailsById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        return user;
    }

    public Users updateUser(Long userId, UserProfileUpdate newDetails) {

        if (userRepository.existsByEmail(newDetails.getEmail())) {
            throw new DuplicateCredentialsException("Email already registered by another account.");
        }

        Users user = findUserById(userId);

        user.setEmail(newDetails.getEmail());
        user.setName(newDetails.getName());

        user = userRepository.save(user);

        return user;
    }

    public Users adminUpdateUser(Long userId, AdminUserUpdate newDetails) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Account Not Found.");
        }

        Users user = findUserById(userId);

        if (newDetails.getEnabled() != null) {
            user.setEnabled(newDetails.getEnabled());
        }

        if (newDetails.getRole() != null) {
            user.setRole(newDetails.getRole());
        }

        user = userRepository.save(user);

        return user;
    }

    public void deleteUser(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Account Not Found.");
        }

        Users user = findUserById(userId);

        user.setEnabled(false);

        userRepository.save(user);
    }

}
