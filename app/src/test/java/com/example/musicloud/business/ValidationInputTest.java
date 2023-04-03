package com.example.musicloud.business;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ValidationInputTest {

    private final ValidationInput validationInput = new ValidationInput();

    @Test
    public void testValidInput() throws ValidateException {
        // Arrange
        String userID = "user123";
        String password = "pass123";
        String fullName = "John Doe";

        // Act
        validationInput.validateInput(userID, password, fullName);

        // Assert - no exception thrown, test passes
    }

    @Test
    public void testInvalidUserID() {
        // Arrange
        String userID = "user 123"; // contains space
        String password = "pass123";
        String fullName = "John Doe";

        // Act and Assert
        ValidateException exception = assertThrows(ValidateException.class,
                () -> validationInput.validateInput(userID, password, fullName));

        assertEquals("User ID, password, and full name cannot be empty, and must be alphanumeric and not contain spaces", exception.getMessage());
    }

    @Test
    public void testInvalidPassword() {
        // Arrange
        String userID = "user123";
        String password = "p"; // too short
        String fullName = "John Doe";

        // Act and Assert
        ValidateException exception = assertThrows(ValidateException.class,
                () -> validationInput.validateInput(userID, password, fullName));

        assertEquals("Password must be at least 2 characters long", exception.getMessage());
    }

    @Test
    public void testEmptyInput() {
        // Arrange
        String userID = "";
        String password = "pass123";
        String fullName = "";

        // Act and Assert
        ValidateException exception = assertThrows(ValidateException.class,
                () -> validationInput.validateInput(userID, password, fullName));

        assertEquals("User ID, password, and full name cannot be empty, and must be alphanumeric and not contain spaces", exception.getMessage());
    }
}
