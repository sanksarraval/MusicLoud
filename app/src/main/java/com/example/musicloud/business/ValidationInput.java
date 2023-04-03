package com.example.musicloud.business;

public class ValidationInput {
    /**
     * validateInput: Validates the Input from the User.
     * */
    public void validateInput(String userID, String password, String fullName) throws ValidateException {
        // UserID and Password should not contain spaces and must match a regex expression.
        if (userID.isEmpty() || password.isEmpty() || fullName.isEmpty() ||
                !userID.matches("^[a-zA-Z0-9]+$")) {
            throw new ValidateException("User ID, password, and full name cannot be empty, and must be alphanumeric and not contain spaces");
        }

        // Password length should be greater than 2.
        else if (password.length() < 2) {
            throw new ValidateException("Password must be at least 2 characters long");
        }
    }
}