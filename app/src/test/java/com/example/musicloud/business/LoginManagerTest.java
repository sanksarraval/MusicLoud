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
        String username = "john";
        String password = "password";
        when(accessUsersMock.verifyUser(username, password)).thenReturn(true);

        boolean result = loginManager.login(username, password);

        assertTrue(result);
    }
    @Test
    public void testLoginWithInvalidCredentials() {
        String username = "john";
        String password = "password";
        when(accessUsersMock.verifyUser(username, password)).thenReturn(false);

        boolean result = loginManager.login(username, password);

        assertFalse(result);
    }
    @Test
    public void testGetCurrentUserWithValidUsername() {
        String username = "john";
        User user = new User("1",username, "password");
        when(accessUsersMock.returnAccount(username)).thenReturn(user);

        User result = loginManager.getCurrentUser(username);

        assertEquals(user, result);
    }
    @Test
    public void testGetCurrentUserWithInvalidUsername() {
        String username = "john";
        when(accessUsersMock.returnAccount(username)).thenReturn(null);

        User result = loginManager.getCurrentUser(username);

        assertNull(result);
    }
}
