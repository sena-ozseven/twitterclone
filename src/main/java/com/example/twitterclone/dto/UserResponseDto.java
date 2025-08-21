package com.example.twitterclone.dto;


//Response --> does not do validation

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        LocalDateTime createdAt
) {}
