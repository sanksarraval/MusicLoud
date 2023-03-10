package com.example.musicloud.business;

import com.example.musicloud.objects.User;

public class LoginManager {
    /**
     * AccessUsers instance
     * */
    private AccessUsers accessUsers;

    /**
     * LoginManager Constructor
     * */
    public LoginManager(AccessUsers accessUsers) {
        this.accessUsers = accessUsers;
    }

    /**
     * login
     * @return True if the user is verified.
     * */
    public boolean login(String username, String password) {
        if (accessUsers.verifyUser(username, password)) {
            // Return true if the user is verified
            return true;
        } else {
            // Return false if the user is not verified
            return false;
        }
    }
    /**
     * getCurrentUser
     * @return The User Object
     * */
    public User getCurrentUser(String username) {
        return accessUsers.returnAccount(username);
    }
}
