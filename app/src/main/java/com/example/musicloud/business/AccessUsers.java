package com.example.musicloud.business;

import com.example.musicloud.objects.User;
import com.example.musicloud.application.Services;
import com.example.musicloud.persistence.UserManagement;

import java.util.List;

public class AccessUsers {

    private final UserManagement userManagement;

    public AccessUsers()
    {
        userManagement = Services.getAccountManagement();
        User user = null;
        int currUser = 0;
    }
    public AccessUsers(UserManagement UM)
    {
        userManagement = UM;
        User user = null;
        int currUser = 0;
    }
    public List<User> getAccounts()
    {
        return userManagement.getAllUsers();
    }

     public boolean verifyUser(String userID, String password)
     {
         return userManagement.verifyUser(userID, password);
     }

     public boolean accountFound(String userID)
     {
         return userManagement.getUser(userID) != null;
     }

     public User returnAccount(String userID)
     {
         User returnUser = null;
         if(userManagement.getUser(userID)!= null)
         {
             returnUser = userManagement.getUser(userID);
         }
         return returnUser;
     }

     public void addAccount(User newUser)
     {
         if(newUser != null)
         {
            userManagement.addAccount(newUser);
         }
     }
}
