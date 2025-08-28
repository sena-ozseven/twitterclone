package com.example.twitterclone.mapper;

import com.example.twitterclone.dto.UserRegisterRequestDto;
import com.example.twitterclone.dto.UserResponseDto;
import com.example.twitterclone.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component   //--> sınıfın bir spring bean olmasını sağlar, böylece service'e inject edebiliriz.
public class UserMapper {

    //UserRegisterRequestDto'dan yeni bir user entity'si oluşturur.
    public User toEntity(UserRegisterRequestDto registerDto) {
        if (registerDto == null) {
            return null;
        }
        User user = new User();
        user.setUsername(registerDto.username());
        user.setEmail(registerDto.email());
        user.setPassword(registerDto.password()); // burada şifre encrypt edilmiyor!!
        return user;

    }

    //update gereksiz --> if (dto.field() != null) kontrolleriyle yapılacak.

    //dış dünyaya
    public UserResponseDto toResponseDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getProfileImageUrl(),
                user.getHeaderImageUrl(),
                user.getCreatedAt()
        );
    }
}
