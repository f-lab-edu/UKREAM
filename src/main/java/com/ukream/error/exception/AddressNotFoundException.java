package com.ukream.error.exception;

public class AddressNotFoundException extends RuntimeException {
  public AddressNotFoundException() {
    super("해당 주소를 찾을 수 없습니다.");
  }

  public AddressNotFoundException(String msg) {
    super(msg);
  }
}