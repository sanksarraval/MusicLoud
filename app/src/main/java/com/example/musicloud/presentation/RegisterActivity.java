package com.example.musicloud.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.musicloud.R;
import com.example.musicloud.business.AccessUsers;
import com.example.musicloud.objects.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterActivity extends Activity {

    private AccessUsers accessUsers;
    private TextInputEditText userEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText fullNameEditText;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Creating accessUser instance
        accessUsers = new AccessUsers();
        accessUsers.getAccounts();
        
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
            
            
            // Error checking for the input fields.
            if(userID.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "User ID, User name, and password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            // UserID and Password should not contain spaces and must match a regex expression.
            } else if(userID.contains(" ") || password.contains(" ") || !userID.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$")) {
                Toast.makeText(getApplicationContext(), "User ID and password cannot contain spaces and must be alphanumeric", Toast.LENGTH_SHORT).show();
                return;
            // Password length should be greater than 2.
            } else if(password.length() < 2) {
                Toast.makeText(getApplicationContext(), "Password must be at least 2 characters long", Toast.LENGTH_SHORT).show();
                return;
            }

            if(accessUsers.accountFound(userID))
            {
                Toast.makeText(RegisterActivity.this, "Already a user, Please login. Or create a new account!", Toast.LENGTH_SHORT).show();
                // Just to check what's the username and password.
                User already_user = accessUsers.returnAccount(userID);
                String user = already_user.getUserName();
                String pass = already_user.getPassword();
                System.out.println("UserID: " + user + "Password: " + pass);
            }
            else
            {
                User newUser = new User(userID,fullName,password);
                accessUsers.addAccount(newUser);
                Toast.makeText(RegisterActivity.this, "Account has been created successfully!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
