package com.example.twitterclone.repository;

import com.example.twitterclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//PK
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    // login için
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
}
