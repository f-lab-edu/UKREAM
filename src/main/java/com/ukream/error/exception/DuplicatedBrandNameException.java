package com.ukream.error.exception;

public class DuplicatedBrandNameException extends RuntimeException {
  public DuplicatedBrandNameException(){
    super("중복된 브랜드명 입니다.");
  }
  public DuplicatedBrandNameException(String msg) {
    super(msg);
  }
}