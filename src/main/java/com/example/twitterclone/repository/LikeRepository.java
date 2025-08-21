package com.example.twitterclone.repository;

import com.example.twitterclone.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
