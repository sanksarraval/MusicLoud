package com.example.musicloud.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.musicloud.R;
import com.example.musicloud.business.AccessUsers;
import com.example.musicloud.business.AccountAlreadyExistsException;
import com.example.musicloud.business.EmptyUserIDException;
import com.example.musicloud.business.PasswordTooShortException;
import com.example.musicloud.business.ValidateException;
import com.example.musicloud.business.ValidationInput;
import com.example.musicloud.objects.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterActivity extends Activity {

    private AccessUsers accessUsers;
    private TextInputEditText userEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText fullNameEditText;
    private boolean flag = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Creating accessUser instance
        accessUsers = new AccessUsers();

        // Taking the input form the text fields.
        userEditText = findViewById(R.id.user_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        fullNameEditText = findViewById(R.id.full_name_edit_text);

        Button register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(view -> {
            // Taking the input and storing in string.
            String userID = Objects.requireNonNull(userEditText.getText()).toString();
            String password = Objects.requireNonNull(passwordEditText.getText()).toString();
            String fullName = Objects.requireNonNull(fullNameEditText.getText()).toString();
            userID = userID.toLowerCase();

            try {
                ValidationInput validator = new ValidationInput();
                validator.validateInput(userID, password, fullName);
            } catch (EmptyUserIDException e) {
                // Handle empty user ID
                showErrorMessage("User ID cannot be empty.");
                flag = true;
            } catch (PasswordTooShortException e) {
                // Handle password too short
                showErrorMessage("Password must be at least 2 characters long.");
                flag = true;
            } catch (ValidateException e) {
                // Handle other validation errors
                showErrorMessage("Invalid input: " + e.getMessage());
                flag = true;
            }

            try {
                if (!flag) {
                    User newUser = new User(userID, fullName, password);
                    accessUsers.addAccount(newUser);
                    Toast.makeText(RegisterActivity.this, "Account has been created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    startActivity(intent);

                }
            } catch (AccountAlreadyExistsException e) {
                Toast.makeText(RegisterActivity.this, "Error creating account: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
