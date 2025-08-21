package com.example.twitterclone.service;


import com.example.twitterclone.dto.UserLoginRequestDto;
import com.example.twitterclone.dto.UserRegisterRequestDto;
import com.example.twitterclone.dto.UserResponseDto;

import java.util.List;

//where business logic happens
//interface --> contract
public interface UserService {

    //list all users
    List<UserResponseDto> getAllUsers();

    //get user by id
    UserResponseDto getUserById(Long id);


    //register a new user
    UserResponseDto register(UserRegisterRequestDto registerRequest);

    //login request
    UserResponseDto login(UserLoginRequestDto loginRequest);

    //update user
    //delete user
}
