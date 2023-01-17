package com.example.authservice.repository;

import com.example.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.security.AuthProvider;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    @Modifying
    @Query("UPDATE User u SET u.provider = ?2 WHERE u.email = ?1")
    void updateAuthenticationProvider(String email, AuthProvider authProvider);
}
