package org.example.springv3.core.error.ex;

// 유효성 검사
public class Exception400 extends RuntimeException {

    public Exception400(String message) {
        super(message);
    }
}
