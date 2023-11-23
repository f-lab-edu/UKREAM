package com.ukream.error.exception;

public class LoginFailureException extends RuntimeException {
  public LoginFailureException(String msg) {
    super(msg);
  }
}