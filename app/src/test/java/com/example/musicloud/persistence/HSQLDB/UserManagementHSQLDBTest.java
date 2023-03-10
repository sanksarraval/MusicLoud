package com.example.musicloud.persistence.HSQLDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserManagementHSQLDBTest {
    private static UserManagementHSQLDB userManagementHSQLDB;

    @Before
    public void setUp() {
        userManagementHSQLDB = new UserManagementHSQLDB("testdb");
    }

    private void cleanTable() {
        // Clean the Users table before each test
        userManagementHSQLDB.cleanTable();
    }


    private static void tearDown() {
        // Drop the Users table after all tests are done
        userManagementHSQLDB.dropTable();
    }

    @Test
    public void testAddAndGetUser() {
        cleanTable();
        User user = new User("testuser", "testpass", "Test User");
        userManagementHSQLDB.addAccount(user);
        User retrievedUser = userManagementHSQLDB.getUser("testuser");
        assertNotNull(retrievedUser);
        assertEquals(user.getUserID(), retrievedUser.getUserID());
        assertEquals(user.getPassword(), retrievedUser.getPassword());
        assertEquals(user.getUserName(), retrievedUser.getUserName());
        tearDown();
    }

    @Test
    public void testGetAllUsers() {
        cleanTable();
        User user1 = new User("testuser1", "testpass1", "Test User 1");
        User user2 = new User("testuser2", "testpass2", "Test User 2");
        User user3 = new User("testuser3", "testpass3", "Test User 3");
        userManagementHSQLDB.addAccount(user1);
        userManagementHSQLDB.addAccount(user2);
        userManagementHSQLDB.addAccount(user3);
        List<User> users = userManagementHSQLDB.getAllUsers();
        assertEquals(3, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertTrue(users.contains(user3));
        tearDown();
    }

    @Test
    public void testVerifyUser() {
        cleanTable();
        User user = new User("testuser", "testpass", "Test User");
        userManagementHSQLDB.addAccount(user);
        assertTrue(userManagementHSQLDB.verifyUser("testuser", "testpass"));
        assertFalse(userManagementHSQLDB.verifyUser("testuser", "wrongpass"));
        assertFalse(userManagementHSQLDB.verifyUser("wronguser", "testpass"));
        tearDown();
    }
}
