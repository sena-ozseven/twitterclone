package com.example.twitterclone.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //to make it intervene when an error occurs.
public class GlobalExceptionHandler {


    //application'da yazarken bizim throw ettiğimiz hataları  handle edecek. --> yukarıda olması çalışması içim mühim.
    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException) {

    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception) {

    }
}
