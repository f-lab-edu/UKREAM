package com.ukream.error.exception;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException() {
    super("해당 상품을 찾을 수 없습니다.");
  }

  public ProductNotFoundException(String msg) {
    super(msg);
  }
}