package com.example.twitterclone.service;


import com.example.twitterclone.dto.UserLoginRequestDto;
import com.example.twitterclone.dto.UserRegisterRequestDto;
import com.example.twitterclone.dto.UserResponseDto;
import com.example.twitterclone.dto.UserUpdateRequestDto;
import com.example.twitterclone.entity.User;

//where business logic happens
//interface --> contract
public interface UserService {

    //register a new user
    UserResponseDto register(UserRegisterRequestDto registerRequest);

    //login request
    UserResponseDto login(UserLoginRequestDto loginRequest);

    //find user by id
    UserResponseDto findUserProfileById(Long id);

    UserResponseDto updateUserProfile(Long id, UserUpdateRequestDto updateRequest, Long authenticatedUserId);

    //delete user account
    void deleteUserAccount(Long id, Long authenticatedUserId);


    //---------INTERNAL METHODS------------
    //controller katmanından ÇAĞIRMA!


    User findUserById(Long id);

    //find user by username
    User findUserByUsername(String username);
}
