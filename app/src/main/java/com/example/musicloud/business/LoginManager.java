package com.example.musicloud.business;

import com.example.musicloud.objects.User;

public class LoginManager {
    private AccessUsers accessUsers;

    public LoginManager(AccessUsers accessUsers) {
        this.accessUsers = accessUsers;
    }

    public boolean login(String username, String password) {
        if (accessUsers.verifyUser(username, password)) {
            // Return true if the user is verified
            return true;
        } else {
            // Return false if the user is not verified
            return false;
        }
    }

    public User getCurrentUser(String username) {
        return accessUsers.returnAccount(username);
    }
}
