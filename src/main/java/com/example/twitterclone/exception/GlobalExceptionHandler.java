package com.example.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
//RestControllerAdvice --> REST Controller için özel olan advice.
@ControllerAdvice //to make it intervene when an error occurs.
public class GlobalExceptionHandler {

    //method overloading

    //application'da yazarken bizim throw ettiğimiz hataları  handle edecek.
    //bu application'ın herhangi bir yerinde hata çıkarsa buraya düşecek
    @ExceptionHandler(TwitterException.class)
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException) {
        TwitterErrorResponse response = new TwitterErrorResponse(
                twitterException.getMessage(),
                twitterException.getHttpStatus().value(),
                System.currentTimeMillis(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, twitterException.getHttpStatus());
    }


    //CATCH-ALL MEACHANISM
    //http status gelmeyecek, biz yazacağız: bizim düşünemediğimiz durumlarda hata çıkarsa: 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception) {
        TwitterErrorResponse response = new TwitterErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
