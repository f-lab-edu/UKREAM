package com.ukream.error.exception;

public class SalesAccountNotFoundException extends RuntimeException {
  public SalesAccountNotFoundException() {
    super("해당 판매 정산 계좌를 찾을 수 없습니다.");
  }

  public SalesAccountNotFoundException(String msg) {
    super(msg);
  }
}