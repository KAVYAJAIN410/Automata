package com.example.Zapy.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Zapy.model.User;

public interface AppUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByGoogleId(String googleId);
}
