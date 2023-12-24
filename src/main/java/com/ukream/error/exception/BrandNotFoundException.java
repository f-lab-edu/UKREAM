package com.ukream.error.exception;

public class BrandNotFoundException extends RuntimeException {
  public BrandNotFoundException(){
    super("해당 브랜드를 찾을 수 없습니다.");
  }
  public BrandNotFoundException(String msg) {
    super(msg);
  }
}