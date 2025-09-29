package com.example.twitterclone.repository;

import com.example.twitterclone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTweetIdOrderByCreatedAtDesc(Long tweetId);
}
