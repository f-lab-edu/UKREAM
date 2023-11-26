package com.ukream.error.exception;

public class PaymentInfoNotFoundException extends RuntimeException {
  public PaymentInfoNotFoundException() {
    super("해당 결제 정보를 찾을 수 없습니다.");
  }

  public PaymentInfoNotFoundException(String msg) {
    super(msg);
  }
}