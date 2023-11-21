package com.ukream.error.exception;

public class DuplicatedModelNumberException extends RuntimeException {
  public DuplicatedModelNumberException() {
    super("중복된 모델번호 입니다.");
  }

  public DuplicatedModelNumberException(String msg) {
    super(msg);
  }
}