package com.example.twitterclone.repository;

import com.example.twitterclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//REPOSITORY
//1. abstraction (flexibility)
//2. crud
//3. query derivation
//4.object-relational mapping (orm)

//PK
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    // login için
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
}
