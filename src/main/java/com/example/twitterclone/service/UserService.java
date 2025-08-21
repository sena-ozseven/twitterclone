package com.example.twitterclone.service;


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
    //update user
    //delete user
}
