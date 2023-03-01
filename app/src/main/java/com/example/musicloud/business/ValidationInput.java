package com.example.musicloud.business;

public class ValidationInput {
    public void validateInput(String userID, String password, String fullName) throws ValidateException {
        // UserID and Password should not contain spaces and must match a regex expression.
        if (userID.contains(" ") || password.contains(" ") || !userID.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$")) {
            throw new ValidateException("User ID and password cannot contain spaces and must be alphanumeric");
        }

        // Password length should be greater than 2.
        if (password.length() < 2) {
            throw new ValidateException("Password must be at least 2 characters long");
        }

        // User ID, User name, and password cannot be empty.
        if (userID.isEmpty() || fullName.isEmpty()) {
            throw new ValidateException("User ID, User name, and password cannot be empty");
        }
    }
}