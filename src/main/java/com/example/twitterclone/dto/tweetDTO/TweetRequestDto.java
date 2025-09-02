package com.example.twitterclone.dto.tweetDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//yeni bir tweet oluşturmak için frontend'den gelecek veri
public record TweetRequestDto(
        @NotBlank(message = "Tweet content cannot be blank")
        @Size(max = 280, message = "Tweet cannot exceed 280 characters")
        String content
) {
}
