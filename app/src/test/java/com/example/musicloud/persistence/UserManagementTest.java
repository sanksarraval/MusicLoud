package com.example.musicloud.persistence;

import static org.junit.Assert.*;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.stubs.UserManagementStub;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class UserManagementTest {

    private UserManagementStub userManagement;
    private User user1;
    private User user2;
    private User user3;

    LinkedList<User> testUserList = new LinkedList<>();

    @Before
    public void setUp()
    {
        userManagement = new UserManagementStub();
        testUserList.addAll(UserManagementStub.userList);

        user1 = new User("User1","userName1","password1");
        user2 = new User("User2","userName2","password2");
        user3 = new User("User3","userName3","password3");

        userManagement.addAccount(user1);
        userManagement.addAccount(user2);
        userManagement.addAccount(user3);
    }

    @Test
    public void testVerifyUser()
    {
        System.out.println("\n****************Starting testVerifyUser**************");

        assertTrue(userManagement.verifyUser("User1","password1"));
        assertTrue(userManagement.verifyUser("User2","password2"));
        assertTrue(userManagement.verifyUser("User3","password3"));

        // Verifying the users that are not present.
        assertFalse("UserID not found, should fail!", userManagement.verifyUser("user4","password4"));
        assertFalse("Wrong password entered!. Should fail! ", userManagement.verifyUser("user1","password2"));

        System.out.println("\n****************Finished testVerifyUser**************");
    }

    @Test
    public void testGetUser()
    {
        System.out.println("\n****************Starting testGetUser**************");

        // comparing the userID, should pass.
        assertEquals(user1,userManagement.getUser("User1"));
        assertEquals(user2,userManagement.getUser("User2"));
        assertEquals(user3,userManagement.getUser("User3"));

        // UserID is not equal, should fail.
        assertNotEquals("The userID does not match. Should Fail",user1,userManagement.getUser("User2"));

        System.out.println("\n****************Finished testGetUser**************");
    }

    @Test
    public void testGetAllUsers()
    {
        System.out.println("\n****************Starting testGetAllUsers**************");

        // Addind the users to the UserList
        testUserList.add(user1);
        testUserList.add(user2);
        testUserList.add(user3);

        // Asserting that the testUserList and the getAllUsers will be equal.
        assertEquals("All the users should match!",testUserList,userManagement.getAllUsers());

        System.out.println("\n****************Finished testGetAllUsers**************");
    }

    @Test
    public void testAddAccount()
    {
        System.out.println("\n****************Starting testAddAccount**************");

        // Creating new users
        User newUser1 = new User("newUser1", "newUserName1","newPassword1");
        User newUser2 = new User("newUser1", "newUserName2","newPassword2");


        // Both the users have the same userID, should not add duplicate users.
        assertEquals(newUser1, userManagement.addAccount(newUser1));
        assertNull("Duplicate UserID should not be added.",userManagement.addAccount(newUser2));

        System.out.println("\n****************Finished testAddAccount**************");

    }

}
