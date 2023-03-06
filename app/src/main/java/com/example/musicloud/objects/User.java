package com.example.musicloud.objects;

import java.util.Objects;

public class User {
    private String userID;
    private String userName;
    private String password;

    public User(String userID, String userName, String password)
    {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

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
