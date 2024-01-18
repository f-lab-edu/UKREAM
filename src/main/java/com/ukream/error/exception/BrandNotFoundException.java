package com.ukream.error.exception;

public class BrandNotFoundException extends RuntimeException {
  public BrandNotFoundException(String msg) {
    super(msg);
  }
}