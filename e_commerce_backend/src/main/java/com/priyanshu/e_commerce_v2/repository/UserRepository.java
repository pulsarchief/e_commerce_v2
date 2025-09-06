package com.priyanshu.e_commerce_v2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.priyanshu.e_commerce_v2.entity.user.Users;

public interface UserRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

    Optional<Users> findByUsernameAndEnabledTrue(String username);

    @EntityGraph(attributePaths = { "orders", "userImage" })
    Optional<Users> findWithDetailsById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
