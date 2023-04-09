package com.example.musicloud.stubs;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.UserManagement;

import java.util.LinkedList;
import java.util.List;

public class UserManagementStub implements UserManagement {

    public static LinkedList<User> userList;
    /**
     * UserManagementStub Constructor: Initializes the linked list and populates it with dummy data.
     * */
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
    /**
     * getUser: Returns user
     * @return: User
     * */
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
    /**
     * getAllUsers: Returns a list of all users
     * @return: List<User>
     * */
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
    /**
     * addAccount: Adds an account into the database.
     * @return: User
     * */
    public User addAccount(User newUser)
    {
        User addedUser = null;
        if(getUser(newUser.getUserID()) == null) {
            userList.add(newUser);
            addedUser = newUser;
        }
        return addedUser;
    }

    public void deleteAccount(User newUser)
    {
        int userNumber = userList.indexOf(newUser);
        if(userNumber > 0)
        {
            userList.remove(userNumber);
        }
    }
}
