package com.example.musicloud.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import androidx.annotation.Nullable;

import com.example.musicloud.R;
import com.example.musicloud.business.AccessUsers;
import com.example.musicloud.objects.User;

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
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Validating Inputs
                String userID = usernameEditText.getText().toString();
                String pass = passwordEditText.getText().toString();
                if(accessUsers.verifyUser(userID,pass))
                {
                    User currUser = accessUsers.returnAccount(userID);
                    Toast.makeText(LoginActivity.this, "Welcome!!", Toast.LENGTH_SHORT).show();
                    buttonLoginOnClick(view);
                }
                else if(TextUtils.isEmpty(usernameEditText.getText().toString())|| TextUtils.isEmpty(passwordEditText.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,"Please input Username and password!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please register using the register Button!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void buttonRegisterOnClick(View v)
    {
        Intent loginIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(loginIntent);
    }
    public void buttonLoginOnClick(View v)
    {
        Intent loginIntent = new Intent(LoginActivity.this, LibraryActivity.class);
        LoginActivity.this.startActivity(loginIntent);
    }
}
