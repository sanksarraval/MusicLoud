package com.example.musicloud.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.musicloud.R;
import com.example.musicloud.application.MyApp;
import com.example.musicloud.business.AccessUsers;
import com.example.musicloud.business.LoginManager;
import com.example.musicloud.objects.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LoginActivity extends Activity {
    private EditText usernameEditText, passwordEditText;
    Button loginBtn;

    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        copyDatabaseToDevice();

        accessUsers = new AccessUsers();
        List<User> userList = accessUsers.getAccounts();

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.Password);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Validating Inputs
                String userID = usernameEditText.getText().toString();
                String pass = passwordEditText.getText().toString();

                LoginManager loginManager = new LoginManager(accessUsers);

                if (loginManager.login(userID, pass)) {
                    //User currUser = loginManager.getCurrentUser(userID);
                    Toast.makeText(LoginActivity.this, "Welcome!!", Toast.LENGTH_SHORT).show();
                    buttonLoginOnClick(view);
                } else if (TextUtils.isEmpty(userID) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this,"Please input Username and password!", Toast.LENGTH_LONG).show();
                } else {
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
        Intent loginIntent = new Intent(LoginActivity.this, PlayActivity.class);
        LoginActivity.this.startActivity(loginIntent);
        finish();
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            MyApp.setDBPathName(dataDirectory.toString() + "/" + MyApp.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

}
