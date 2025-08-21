package com.example.twitterclone.service;

import com.example.twitterclone.dto.UserLoginRequestDto;
import com.example.twitterclone.dto.UserRegisterRequestDto;
import com.example.twitterclone.dto.UserResponseDto;
import com.example.twitterclone.dto.UserUpdateRequestDto;
import com.example.twitterclone.entity.User;
import com.example.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //--> consturctor'ı yazıyor. required <--> final
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

//MAPPERRRRRR
    @Override
    public UserResponseDto register(UserRegisterRequestDto registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());

    }

    @Override
    public UserResponseDto login(UserLoginRequestDto loginRequest) {
        return null;
    }

    @Override
    public UserResponseDto findUserProfileById(Long id) {
        return null;
    }

    @Override
    public UserResponseDto updateUserProfile(Long id, UserUpdateRequestDto updateRequest, Long authenticatedUserId) {
        return null;
    }

    @Override
    public void deleteUserAccount(Long id, Long authenticatedUserId) {

    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }
}
