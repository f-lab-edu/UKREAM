package com.ukream.error.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("해당 유저를 찾을 수 없습니다.");
  }

  public UserNotFoundException(String msg) {
    super(msg);
  }
}