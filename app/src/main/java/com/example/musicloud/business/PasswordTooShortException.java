package com.example.musicloud.business;

public class PasswordTooShortException extends ValidateException {
    public PasswordTooShortException(String message) {
        super(message);
    }
}
