package com.example.musicloud.persistence.HSQLDB;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;

import org.junit.Before;
import org.junit.Test;

public class UserManagementHSQLDBIntegrationTest {
    private static UserManagementHSQLDB userManagementHSQLDB;

    @Before
    public void setUp() {
        userManagementHSQLDB = new UserManagementHSQLDB("src/test/HSQLDB/app_db/SC");
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
    public void testAddGetVerifyUser() {
        //Get all local users
        for (User user : userManagementHSQLDB.getAllUsers()) {
            System.out.println("userId==" + user.getUserID() + ", userName==" + user.getUserName() + ", userPassword==" + user.getPassword());
        }

        //The second step is to simulate the login
        String userId = "testuser";
        String userName = "Test User";
        String userPassword = "testpass";
        boolean isExisted = userManagementHSQLDB.verifyUser(userId, userPassword);
        if (isExisted) {
            //User presence
            System.out.println("Login success");
        } else {
            System.out.println("Start registration");
            User user = new User(userId, userName, userPassword);
            user = userManagementHSQLDB.addAccount(user);
            if (user == null) {
                System.out.println("Registration failure");
            } else {
                System.out.println("Successful registration");
                //Log in again
                if (userManagementHSQLDB.verifyUser(userId, userPassword)) {
                    System.out.println("Login success");
                }
            }
        }
        //Print all users again
        for (User user : userManagementHSQLDB.getAllUsers()) {
            System.out.println("userId==" + user.getUserID() + ", userName==" + user.getUserName() + ", userPassword==" + user.getPassword());
        }
    }

}
