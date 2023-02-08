package com.example.musicloud.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import androidx.annotation.Nullable;

import com.example.musicloud.MainActivity;
import com.example.musicloud.R;
import com.example.musicloud.business.AccessUsers;
import com.example.musicloud.objects.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends Activity {
    private EditText usernameEditText, passwordEditText;
    Button loginBtn;

    private AccessUsers accessUsers;
    private List<User> userList;

    String correct_username = "admin";
    String correct_password = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accessUsers = new AccessUsers();
        userList = accessUsers.getAccounts();

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.Password);
        loginBtn = findViewById(R.id.button);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Validating Inputs
                if(TextUtils.isEmpty(usernameEditText.getText().toString())|| TextUtils.isEmpty(passwordEditText.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,"Please input Username and password!", Toast.LENGTH_LONG).show();
                } else if (usernameEditText.getText().toString().equals(correct_username))
                {
                    if(passwordEditText.getText().toString().equals(correct_password))
                    {
                        Toast.makeText(LoginActivity.this,"Login was successful", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Incorrect Password!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Invalid Username/password!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
