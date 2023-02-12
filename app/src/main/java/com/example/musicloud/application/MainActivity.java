package com.example.musicloud.application;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.musicloud.R;
import com.example.musicloud.presentation.LoginActivity;


public class MainActivity extends Activity {

    EditText username, password;
    Button loginBtn;

    String correct_username = "admin";
    String correct_password = "admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        username = findViewById(R.id.username);
        password = findViewById(R.id.Password);
        loginBtn = findViewById(R.id.button);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validating Inputs
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please input Username and password!", Toast.LENGTH_LONG).show();
                } else if (username.getText().toString().equals(correct_username)) {
                    if (password.getText().toString().equals(correct_password)) {
                        Toast.makeText(MainActivity.this, "Login was successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this, PlayActivity.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect Password!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Username/password!", Toast.LENGTH_LONG).show();
                }
            }
        });
        */
    }
    public void buttonLoginOnClick(View v)
    {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(loginIntent);
    }
}