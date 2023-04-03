package com.example.musicloud.business;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.UserManagement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AccessUsersTest {

    private AccessUsers accessUsers;
    private UserManagement userManagement;

    @Before
    public void setUp() {
        userManagement = mock(UserManagement.class);
        accessUsers = new AccessUsers(userManagement);
    }

    @Test
    public void testGetAccounts() {
        List<User> expectedUsers = new ArrayList<>();
        User user1 = new User("id1", "User1","password1");
        User user2 = new User("id2", "User2","password2");
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        when(userManagement.getAllUsers()).thenReturn(expectedUsers);
        List<User> actualUsers = accessUsers.getAccounts();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testVerifyUser() {
        String userID = "testID";
        String password = "testPassword";

        when(userManagement.verifyUser(userID, password)).thenReturn(true);
        boolean result = accessUsers.verifyUser(userID, password);
        assertTrue(result);
    }

    @Test
    public void testAccountFound() {
        String userID = "testID";
        User user = new User(userID, "User1","password");

        when(userManagement.getUser(userID)).thenReturn(user);
        boolean result = accessUsers.accountFound(userID);
        assertTrue(result);
    }

    @Test
    public void testReturnAccount() {
        String userID = "testID";
        User user = new User(userID, "User1","password");

        when(userManagement.getUser(userID)).thenReturn(user);
        User returnedUser = accessUsers.returnAccount(userID);
        assertEquals(user, returnedUser);
    }

    @Test
    public void testAddAccount() {
        User user1 = new User("testID", "User1", "password");
        try {
        // Add the first user
        accessUsers.addAccount(user1);
        verify(userManagement, times(1)).addAccount(user1);
        } catch (AccountAlreadyExistsException e) {
            // Expected exception was thrown
        }

        // Verify that the second user was not added
        verify(userManagement, times(1)).addAccount(user1);
    }
}
