package com.ukream.error.exception;

public class LoginRequiredException extends RuntimeException {
  public LoginRequiredException(String msg) {
    super(msg);
  }
}