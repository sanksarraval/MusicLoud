package com.srsys.musicloud.persistence;

import com.srsys.musicloud.objects.User;

import java.util.List;

public interface UserManagement {

    List<User> getAllUsers();

    User getUser (String userID);

    boolean verifyUser(String userID, String password);

    User addAccount(User newUser);


    /*

    Implemented but not used in the UI

    void deleteAccount(User newUser);

    void updatePassword(User account, String newPassword);
    void updateUserName(User account, String newUserID);
    */
}
