package com.example.twitterclone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UserUpdateRequestDto(
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @Email(message = "Please provide a valid email address")
        String email,

        @Size(max = 160, message = "Bio cannot exceed 160 characters")
        String bio,

        @URL(message = "Please provide a valid URL for the profile image")
        String profileImageUrl,

        @URL(message = "Please provide a valid URL for the header image")
        String headerImageUrl
) {
}
