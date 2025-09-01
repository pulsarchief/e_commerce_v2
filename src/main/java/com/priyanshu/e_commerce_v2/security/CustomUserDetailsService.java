package com.priyanshu.e_commerce_v2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.exception.UserNotFoundException;
import com.priyanshu.e_commerce_v2.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository
                .findByUsernameAndArchivedFalse(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return new CustomUserDetails(user);

    }

}
