package com.example.twitterclone.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends TwitterException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
