package com.example.twitterclone.controller;

import com.example.twitterclone.dto.UserResponseDto;
import com.example.twitterclone.dto.UserUpdateRequestDto;
import com.example.twitterclone.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserProfileById(@PathVariable Long id) {
        // @PathVariable: URL'deki {id} değerini metodun id parametresine atar.

        UserResponseDto userProfile = userService.findUserProfileById(id);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserProfile(@PathVariable Long id,
                                                             @Valid @RequestBody UserUpdateRequestDto updateRequest) {
        // ÖNEMLİ NOT: authenticatedUserId'yi şimdilik manuel olarak giriyoruz.
        // Spring Security eklediğimizde, bu bilgiyi o an giriş yapmış olan
        // kullanıcının token'ından alacağız.
        Long authenticatedUserId = 1L; // GEÇİCİ OLARAK 1. KULLANICIYI VARSAYIYORUZ

        UserResponseDto updatedUser = userService.updateUserProfile(id, updateRequest, authenticatedUserId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        // Yukarıdaki gibi, authenticatedUserId'yi şimdilik manuel giriyoruz.
        Long authenticatedUserId = 1L; // GEÇİCİ OLARAK 1. KULLANICIYI VARSAYIYORUZ

        userService.deleteUserAccount(id, authenticatedUserId);

        // Bir şey silindiğinde genellikle HTTP 204 No Content durumu döndürülür.
        // Bu, işlemin başarılı olduğunu ama döndürülecek bir içerik olmadığını belirtir.
        return ResponseEntity.noContent().build();
    }

}
