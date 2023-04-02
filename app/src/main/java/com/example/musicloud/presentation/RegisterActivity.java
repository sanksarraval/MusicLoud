package com.example.musicloud.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.musicloud.R;
import com.example.musicloud.application.MyApp;
import com.example.musicloud.business.AccessUsers;
import com.example.musicloud.business.EmptyUserIDException;
import com.example.musicloud.business.PasswordTooShortException;
import com.example.musicloud.business.ValidationInput;
import com.example.musicloud.business.ValidateException;
import com.example.musicloud.objects.User;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class RegisterActivity extends Activity {

    private AccessUsers accessUsers;
    private TextInputEditText userEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText fullNameEditText;
    private final boolean flag = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        copyDatabaseToDevice();

        // Creating accessUser instance
        accessUsers = new AccessUsers();
        //accessUsers.getAccounts();
        
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

            ValidationInput vi = new ValidationInput();

            try {
                ValidationInput validator = new ValidationInput();
                validator.validateInput(userID, password, fullName);
            } catch (EmptyUserIDException e) {
                // Handle empty user ID
                showErrorMessage("User ID cannot be empty.");
            } catch (PasswordTooShortException e) {
                // Handle password too short
                showErrorMessage("Password must be at least 2 characters long.");
            } catch (ValidateException e) {
                // Handle other validation errors
                showErrorMessage("Invalid input: " + e.getMessage());
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
            else if(!accessUsers.accountFound(userID) && !flag)
            {
                User newUser = new User(userID,fullName,password);
                accessUsers.addAccount(newUser);
                Toast.makeText(RegisterActivity.this, "Account has been created successfully!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
