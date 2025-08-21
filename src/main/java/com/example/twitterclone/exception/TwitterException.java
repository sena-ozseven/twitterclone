package com.example.twitterclone.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TwitterException extends RuntimeException {

  private HttpStatus httpStatus;

  //                      method signatures
  public TwitterException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
