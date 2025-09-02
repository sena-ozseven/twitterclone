package com.example.twitterclone.dto;

import jakarta.validation.constraints.NotBlank;


//Dto --> data transfer object


public record UserLoginRequestDto(
        @NotBlank(message = "Username cannot be blank")
        String username,

        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
