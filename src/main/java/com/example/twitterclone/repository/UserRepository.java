package com.example.twitterclone.repository;

import com.example.twitterclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
                                                            //PK 
public interface UserRepository extends JpaRepository<User, Long> {
}
