package com.example.twitterclone.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends TwitterException{
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
