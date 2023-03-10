package com.example.musicloud.persistence;

import com.example.musicloud.objects.User;

import java.util.List;

public interface UserManagement {
    /**
     * returns list of all Users objects.
     */
    List<User> getAllUsers();
    /**
     * returns Users objects.
     */
    User getUser (String userID);
    /**
     * Verifies the user and returns boolean.
     */
    boolean verifyUser(String userID, String password);
    /**
     * Adds the user account
     */
    User addAccount(User newUser);

}
