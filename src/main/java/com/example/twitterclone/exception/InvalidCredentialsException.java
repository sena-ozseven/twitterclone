package com.example.twitterclone.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends TwitterException{
    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
