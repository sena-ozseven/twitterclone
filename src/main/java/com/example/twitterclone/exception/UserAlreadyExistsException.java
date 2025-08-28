package com.example.twitterclone.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends TwitterException {

    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
