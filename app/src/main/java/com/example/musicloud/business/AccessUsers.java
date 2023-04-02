package com.example.musicloud.business;

import com.example.musicloud.objects.User;
import com.example.musicloud.application.Services;
import com.example.musicloud.persistence.UserManagement;

import java.util.List;

public class AccessUsers {
    /**
     * UserManagement instance
     * */
    private final UserManagement userManagement;

    /**
     * AccessUsers Constructor
     * */
    public AccessUsers()
    {
        userManagement = Services.getAccountManagement();
        User user = null;
        int currUser = 0;
    }

    /**
     * AccessUsers Constructor
     * @param UM - UserManagement instance for Testing
     * */
    public AccessUsers(UserManagement UM)
    {
        userManagement = UM;
        User user = null;
        int currUser = 0;
    }
    /**
     * getAccounts
     * @return List<Users> Users list
     * */
    public List<User> getAccounts()
    {
        return userManagement.getAllUsers();
    }

    /**
     * verifyUser
     * @return boolean value if the user was verified.
     * */
     public boolean verifyUser(String userID, String password)
     {
         return userManagement.verifyUser(userID, password);
     }
    /**
     * accountFound
     * @return boolean value if the account was found.
     * */
     public boolean accountFound(String userID)
     {
         return userManagement.getUser(userID) != null;
     }

     /**
     * returnAccount
     * @return The User object.
     * */
     public User returnAccount(String userID)
     {
         User returnUser = null;
         if(userManagement.getUser(userID)!= null)
         {
             returnUser = userManagement.getUser(userID);
         }
         return returnUser;
     }

    /**
     * addAccount Adds a new account.
     * */
    public void addAccount(User newUser) throws AccountAlreadyExistsException {
        if (accountFound(newUser.getUserName())) {
            throw new AccountAlreadyExistsException("User already exists with ID: " + newUser.getUserName());
        }
        userManagement.addAccount(newUser);
    }
}
