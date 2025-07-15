package com.ydm.j2ee.core.exception;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(String message) {
        super(message);
    }
}
