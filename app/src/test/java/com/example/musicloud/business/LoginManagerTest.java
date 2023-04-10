package com.example.musicloud.business;

import com.example.musicloud.objects.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class LoginManagerTest {
    private AccessUsers accessUsersMock;
    private LoginManager loginManager;

    @Before
    public void setUp() {
        accessUsersMock = mock(AccessUsers.class);
        loginManager = new LoginManager(accessUsersMock);
    }
    @Test
    public void testLoginWithValidCredentials() {
        System.out.println("\n*********** Starting testLoginWithValidCredentials ***********");
        String username = "john";
        String password = "password";
        when(accessUsersMock.verifyUser(username, password)).thenReturn(true);

        boolean result = loginManager.login(username, password);

        assertTrue(result);
        System.out.println("\n*********** Finished testLoginWithValidCredentials ***********");
    }
    @Test
    public void testLoginWithInvalidCredentials() {
        System.out.println("\n*********** Starting testLoginWithInvalidCredentials ***********");
        String username = "john";
        String password = "password";
        when(accessUsersMock.verifyUser(username, password)).thenReturn(false);

        boolean result = loginManager.login(username, password);

        assertFalse(result);
        System.out.println("\n*********** Finished testLoginWithInvalidCredentials ***********");
    }
    @Test
    public void testGetCurrentUserWithValidUsername() {
        System.out.println("\n*********** Starting testGetCurrentUserWithValidUsername ***********");
        String username = "john";
        User user = new User("1",username, "password");
        when(accessUsersMock.returnAccount(username)).thenReturn(user);

        User result = loginManager.getCurrentUser(username);

        assertEquals(user, result);
        System.out.println("\n*********** Finished testGetCurrentUserWithValidUsername ***********");
    }
    @Test
    public void testGetCurrentUserWithInvalidUsername() {
        System.out.println("\n*********** Starting testGetCurrentUserWithInvalidUsername ***********");
        String username = "john";
        when(accessUsersMock.returnAccount(username)).thenReturn(null);

        User result = loginManager.getCurrentUser(username);

        assertNull(result);
        System.out.println("\n*********** Finished testGetCurrentUserWithInvalidUsername ***********");
    }
}
