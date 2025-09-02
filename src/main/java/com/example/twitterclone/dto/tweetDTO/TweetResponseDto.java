package com.example.twitterclone.dto.tweetDTO;


import java.time.LocalDateTime;

//tweet'i frontend'e gösterirken göndereceğimiz veri
public record TweetResponseDto(
        Long id,
        String content,
        LocalDateTime createdAt,
        Long userId,
        String username,
        int likeCount
) {
}
