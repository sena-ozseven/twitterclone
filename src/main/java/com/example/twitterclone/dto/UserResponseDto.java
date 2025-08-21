package com.example.twitterclone.dto;


//Response --> does not do validation

public record UserResponseDto(String username, String email, String password) {
}
