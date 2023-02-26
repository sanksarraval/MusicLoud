package com.srsys.musicloud.business;

import com.srsys.musicloud.objects.User;
import com.srsys.musicloud.application.Services;
import com.srsys.musicloud.persistence.UserManagement;

import java.util.List;

public class AccessUsers {

    private UserManagement userManagement;
    private List<User> users;

    private User user;

    private int currUser;

    public AccessUsers()
    {
        userManagement = Services.getAccountManagement();
        users = null;
        user = null;
        currUser = 0;
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
         boolean found = false;
         if(userManagement.getUser(userID) != null)
         {
             found = true;
         }
         return found;
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
    /*

    Implemented but not used in the UI

     public void updateUserName(User user, String userName)
     {
         userManagement.updateUserName(user,userName);
     }

     public void updatePassword(User user, String pass)
     {
         userManagement.updatePassword(user,pass);
     }

     */

}
