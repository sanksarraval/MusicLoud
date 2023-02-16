package com.example.musicloud.persistence.stubs;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.UserManagement;

import java.util.LinkedList;
import java.util.List;

public class UserManagementStub implements UserManagement {

    public static LinkedList<User> userList;

    public UserManagementStub()
    {
        userList = new LinkedList<>();

        User newUser1 = new User("ravals1", "Sanskar Raval", "Sanskar123");
        User newUser2 = new User("chauhana","Aakash Chaohan","Aakash123");
        User newUser3 = new User("seraspij","Jacob Seraspi","Jacob123");
        User newUser4 = new User ("admin", "admin", "admin");
        userList.add(newUser1);
        userList.add(newUser2);
        userList.add(newUser3);
        userList.add(newUser4);
    }

    public User getUser(String userID)
    {
        int found = -1;
        boolean flag = false;
        for(int i = 0; i<userList.size();i++)
        {
            String id = userList.get(i).getUserID();
            if(id.equals(userID) && !flag)
            {
                found = i;
                flag = true;
            }
        }

        if(found == -1)
        {
            //System.out.println("User not found");
            return null;
        }
        else
        {
            return userList.get( found );
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    public boolean verifyUser( String userID , String password )
    {
        boolean flag = false;

        User userValidate = getUser( userID );
        if ( userValidate != null )
        {
            if ( userValidate.getPassword().equals(password))
            {
                flag = true;
            }
        }
        return flag;
    }

    public User addAccount(User newUser)
    {
        User addedUser = null;
        if(getUser(newUser.getUserID()) == null) {
            userList.add(newUser);
            addedUser = newUser;
        }
        return addedUser;
    }
    /*

    Implemented but not used in the UI

    public void deleteAccount(User newUser)
    {
        int userNumber = userList.indexOf(newUser);
        if(userNumber > 0)
        {
            userList.remove(userNumber);
        }
    }

    public void updatePassword(User currentUser, String newPass)
    {
        int userNumber = userList.indexOf(currentUser);
        currentUser.setPassword(newPass);
        //userList.set(userNumber,currentUser);
    }

    public void updateUserName(User currentUser, String newName)
    {
        int userNumber = userList.indexOf(currentUser);
        currentUser.setUserName(newName);
        userList.set(userNumber,currentUser);
    }
     */
}
