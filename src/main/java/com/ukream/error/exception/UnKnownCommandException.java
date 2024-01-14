package com.ukream.error.exception;

public class UnKnownCommandException extends RuntimeException {
    public UnKnownCommandException() {
        super("해당 명령을 알 수 없습니다.");
    }

    public UnKnownCommandException(String msg) {
        super(msg);
    }
}
