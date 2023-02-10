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


        accessUsers = new AccessUsers();
        accessUsers.getAccounts();

        userEditText = findViewById(R.id.user_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        fullNameEditText = findViewById(R.id.full_name_edit_text);

        Button register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(view -> {

            String userID = Objects.requireNonNull(userEditText.getText()).toString();
            String password = Objects.requireNonNull(passwordEditText.getText()).toString();
            String fullName = Objects.requireNonNull(fullNameEditText.getText()).toString();
            userID = userID.toLowerCase();

            if(accessUsers.accountFound(userID))
            {
                Toast.makeText(RegisterActivity.this, "Already a user, Please login", Toast.LENGTH_SHORT).show();
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
