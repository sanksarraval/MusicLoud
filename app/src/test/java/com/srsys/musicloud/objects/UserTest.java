package com.srsys.musicloud.objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
    @Test
    public void testGetUserName()
    {
        User user, user1, user2, user3;
        System.out.println("\n*********** Starting testGetUserName ***********");

        // Adding users.
        user = new User("0","Joe","123");
        user1 = new User("1","Joe1","1234");
        user2 = new User("2","Joe2","12345");
        user3 = new User("3","Joe3","123456");

        // Asserting User not null.
        assertNotNull(user);
        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);

        // Testing getUserName()
        assertEquals("Joe", user.getUserName());
        assertEquals("Joe1", user1.getUserName());
        assertEquals("Joe2", user2.getUserName());
        assertEquals("Joe3", user3.getUserName());

        System.out.println("*********** Finished testGetUserName ***********");
    }

    @Test
    public void testGetUserID()
    {
        User user, user1, user2, user3;

        System.out.println("\n*********** Starting testGetUserID ***********");

        // Adding users.
        user = new User("0","Joe","123");
        user1 = new User("1","Joe1","1234");
        user2 = new User("2","Joe2","12345");
        user3 = new User("3","Joe3","123456");

        // Asserting User not null.
        assertNotNull(user);
        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);

        assertEquals("0", user.getUserID());
        assertEquals("1", user1.getUserID());
        assertEquals("2", user2.getUserID());
        assertEquals("3", user3.getUserID());

        System.out.println("*********** Finished testGetUserID ***********");
    }

    @Test
    public void testGetUserPassword()
    {
        User user, user1, user2, user3;

        System.out.println("\n*********** Starting testGetUserPassword ***********");

        // Adding users.
        user = new User("0","Joe","123");
        user1 = new User("1","Joe1","1234");
        user2 = new User("2","Joe2","12345");
        user3 = new User("3","Joe3","123456");

        // Asserting User not null.
        assertNotNull(user);
        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);

        assertEquals("123", user.getPassword());
        assertEquals("1234", user1.getPassword());
        assertEquals("12345", user2.getPassword());
        assertEquals("123456", user3.getPassword());
        System.out.println("*********** Finished testGetUserPassword ***********");
    }

    @Test
    public void testSetUserName()
    {
        User user, user1, user2, user3;

        System.out.println("\n*********** Starting testSetUserName ***********");

        // Adding users.
        user = new User("0","Joe","123");
        user1 = new User("1","Joe1","1234");
        user2 = new User("2","Joe2","12345");
        user3 = new User("3","Joe3","123456");

        // Asserting User not null.
        assertNotNull(user);
        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);

        user.setUserName("Sanskar");
        user1.setUserName("Aakash");
        user2.setUserName("Logan");
        user3.setUserName("Lang");

        assertEquals("Sanskar", user.getUserName());
        assertEquals("Aakash", user1.getUserName());
        assertEquals("Logan", user2.getUserName());
        assertEquals("Lang", user3.getUserName());

        System.out.println("*********** Finished testSetUserName ***********");
    }

    @Test
    public void testSetUserID()
    {
        User user, user1, user2, user3;

        System.out.println("\n*********** Starting testSetUserID ***********");

        // Adding users.
        user = new User("0","Joe","123");
        user1 = new User("1","Joe1","1234");
        user2 = new User("2","Joe2","12345");
        user3 = new User("3","Joe3","123456");

        // Asserting User not null.
        assertNotNull(user);
        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);

        user.setUserID("Sanskar0");
        user1.setUserID("Aakash1");
        user2.setUserID("Logan2");
        user3.setUserID("Lang3");

        assertEquals("Sanskar0", user.getUserID());
        assertEquals("Aakash1", user1.getUserID());
        assertEquals("Logan2", user2.getUserID());
        assertEquals("Lang3", user3.getUserID());

        System.out.println("*********** Finished testSetUserID ***********");
    }

    @Test
    public void testSetUserPassword()
    {
        User user, user1, user2, user3;
        System.out.println("\n*********** Starting testSetUserPassword ***********");

        // Adding users.
        user = new User("0","Joe","123");
        user1 = new User("1","Joe1","1234");
        user2 = new User("2","Joe2","12345");
        user3 = new User("3","Joe3","123456");

        // Asserting User not null.
        assertNotNull(user);
        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);

        // Setting Passwords
        user.setPassword("password");
        user1.setPassword("password1");
        user2.setPassword("password2");
        user3.setPassword("password3");

        assertEquals("password", user.getPassword());
        assertEquals("password1", user1.getPassword());
        assertEquals("password2", user2.getPassword());
        assertEquals("password3", user3.getPassword());

        System.out.println("*********** Finished testSetUserPassword ***********");
    }

}
