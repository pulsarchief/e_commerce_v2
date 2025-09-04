
package com.priyanshu.e_commerce_v2.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.priyanshu.e_commerce_v2.entity.user.UserRole;
import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.repository.UserRepository;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner addAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            String adminUsername = "admin";
            String adminEmail = "admin@ecommerce.com";

            if (!userRepository.existsByUsername(adminUsername)) {

                Users admin = new Users();

                admin.setUsername(adminUsername);
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(UserRole.ADMIN);
                admin.setEnabled(true);
                admin.setName("Admin User");

                userRepository.save(admin);
            }
        };
    }
}
