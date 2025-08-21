package com.example.twitterclone.exception;

import org.springframework.http.HttpStatus;

public class TwitterException extends RuntimeException {

  private HttpStatus httpStatus;

  public TwitterException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
