package com.example.musicloud.objects;

import java.util.Objects;

public class User {
    /**
     * Private Access Variables
     * */
    private String userID; // Stores a Unique UserID
    private String userName; // Stores the Users Name
    private String password; // Stores the password

    /**
     * User Constructor
     * @param userID: The userID
     * @param password: The password which user enters
     * @param userName: The User's Name
     * */
    public User(String userID, String userName, String password)
    {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }
    /**
     * Getter Methods: Used for testing
     * */
    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Setter Methods: Used for testing
     * */

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password){this.password = password;}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean equals(Object other){
        boolean equals = false;

        if (other instanceof User)
        {
            final User otherUser = (User) other;
            equals = Objects.equals(this.userID, otherUser.userID);
        }

        return equals;
    }
}
