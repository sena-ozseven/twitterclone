package com.example.twitterclone.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

//Bu record, yeni bir kullanıcı kaydı oluşturmak için (request) API'ye gönderilecek verileri temsil eder.
//Sadece kayıt için gerekli olan alanları içerir (bu case'de email, password, username)
//ve bu alanlar üzerinde validasyon kuralları barındırır.
//Record--> getter, equals, hashCode ve toString metodlarını otomatik olarak oluşturur.

//Dışarıdan gönderdiğimiz veriler JSON (Javascript Object Notation) tipinde oluyor.
//RequestDto --> outcoming data - accept and verify.

public record UserRequestDto(
        @NotEmpty
        @NotNull
        @NotBlank(message = "Username cannot be blank")
        @Size(min= 3, max=50)
        //bunun JSON key'i username'dir demek için:
        @JsonProperty("username")
        String username,

        @NotEmpty
        @NotNull
        @NotBlank
        @Email(message = "Please provide a valid email address")
        @JsonProperty("email")
        String email,

        @NotEmpty
        @NotNull
        @NotBlank
        @Size(min= 8, max = 50)
        @JsonProperty("password")
        String password
) {}
