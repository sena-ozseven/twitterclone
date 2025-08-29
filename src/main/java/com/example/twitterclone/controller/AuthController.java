package com.example.twitterclone.controller;

import com.example.twitterclone.dto.UserLoginRequestDto;
import com.example.twitterclone.dto.UserRegisterRequestDto;
import com.example.twitterclone.dto.UserResponseDto;
import com.example.twitterclone.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//FOR THE OUTER WORLD,, kimlik doğrulaması gerektirmeyen public (/login ve /register) endpointler için.

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterRequestDto registerRequest) {
        // @Valid --> dto'daki validasyonları kontrol etmesi için
        // @RequestBod --> gelen json'ı dto'ya cevirir.
        UserResponseDto registeredUser = userService.register(registerRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto loginRequest) {
        UserResponseDto user = userService.login(loginRequest);
        // HTTP 200 OK
        return ResponseEntity.ok(user);
    }
}
