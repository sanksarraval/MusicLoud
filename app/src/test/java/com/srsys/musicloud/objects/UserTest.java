package com.srsys.musicloud.objects;

import static org.junit.Assert.*;

import com.srsys.musicloud.business.ValidateException;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user, user1, user2, user3;
    @Before
    public void setUp() {

        user = new User("0","Joe","123");
        user1 = new User("1","Joe1","1234");
        user2 = new User("2","Joe2","12345");
        user3 = new User("3","Joe3","123456");
    }
    @Test
    public void testGetUserNameReturnsCorrectUsername() {
        System.out.println("\n*********** Starting testGetUserNameReturnsCorrectUsername ***********");
        assertEquals("Joe", user.getUserName());
        assertEquals("Joe1", user1.getUserName());
        assertEquals("Joe2", user2.getUserName());
        assertEquals("Joe3", user3.getUserName());
        System.out.println("*********** Finished testGetUserNameReturnsCorrectUsername ***********");

    }

    @Test
    public void testGetUserNameWithNullUsername() {
        System.out.println("\n*********** Starting testGetUserNameWithNullUsername ***********");

        user.setUserName(null);
        assertNull(user.getUserName());

        System.out.println("*********** Finished testGetUserNameWithNullUsername ***********");
    }

    @Test
    public void testGetUserNameWithEmptyUsername() {
        System.out.println("\n*********** Starting testGetUserNameWithEmptyUsername ***********");

        user.setUserName("");
        assertEquals("", user.getUserName());

        System.out.println("*********** Finished testGetUserNameWithEmptyUsername ***********");
    }

    @Test
    public void testGetUserIDReturnsCorrectUserID()
    {
        System.out.println("\n*********** Starting testGetUserIDReturnsCorrectUserID ***********");

        assertEquals("0", user.getUserID());
        assertEquals("1", user1.getUserID());
        assertEquals("2", user2.getUserID());
        assertEquals("3", user3.getUserID());

        System.out.println("*********** Finished testGetUserIDReturnsCorrectUserID ***********");
    }


    @Test
    public void testGetUserPassword()
    {

        System.out.println("\n*********** Starting testGetUserPassword ***********");

        assertEquals("123", user.getPassword());
        assertEquals("1234", user1.getPassword());
        assertEquals("12345", user2.getPassword());
        assertEquals("123456", user3.getPassword());
        System.out.println("*********** Finished testGetUserPassword ***********");
    }

    @Test
    public void testSetUserName()
    {
        System.out.println("\n*********** Starting testSetUserName ***********");

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
        System.out.println("\n*********** Starting testSetUserID ***********");
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
        System.out.println("\n*********** Starting testSetUserPassword ***********");

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
