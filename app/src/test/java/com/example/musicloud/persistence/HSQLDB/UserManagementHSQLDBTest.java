package com.example.musicloud.persistence.HSQLDB;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserManagementHSQLDBTest {

    @Mock
    private Connection mConnectionMock;
    @Mock
    private PreparedStatement mStatementMock;
    @Mock
    private ResultSet mResultSetMock;
    private UserManagementHSQLDB mActualUserManagementHSQLDB;

    @Before
    public void setUp() {
        /*
         * The virtual database connection object and the PreparedStatement object are bound to the Mockito framework
         * using the when() and thenReturn() methods
         */
        mActualUserManagementHSQLDB = new UserManagementHSQLDB("src/test/HSQLDB/app_db/SC");
        // Assign a simulated connection to a db object
        mActualUserManagementHSQLDB.setConnection(mConnectionMock);
        // Returns the prepareStatement() method of the simulated object to the simulated PreparedStatement object
        try {
            when(mConnectionMock.prepareStatement(anyString())).thenReturn(mStatementMock);
            when(mStatementMock.executeQuery()).thenReturn(mResultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddAndGetUser() {
        try {
            User user = new User("testuser", "Test User", "testpass");
            user = mActualUserManagementHSQLDB.addAccount(user);
            verify(mConnectionMock, times(1)).prepareStatement(anyString());
            verify(mStatementMock).setString(eq(1), eq("testuser"));
            verify(mStatementMock).setString(eq(2), eq("Test User"));
            verify(mStatementMock).setString(eq(3), eq("testpass"));
            // The executeUpdate() method verifies that the PreparedStatement object is called once
            verify(mStatementMock, times(1)).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllUsers() {
        // Returns the prepareStatement() method of the simulated object to the simulated PreparedStatement object
        Statement statementMock = mock(Statement.class);
        try {
            when(mConnectionMock.createStatement()).thenReturn(statementMock);
            when(statementMock.executeQuery(anyString())).thenReturn(mResultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Simulate the expected  data
        List<User> userList = new ArrayList<>();
        userList.add(new User("testuser1", "Test User 1", "testpass1"));
        userList.add(new User("testuser2", "Test User 2", "testpass2"));
        userList.add(new User("testuser3", "Test User 3", "testpass3"));
        List<String> userIdList = new ArrayList<>();
        List<String> userNameList = new ArrayList<>();
        List<String> userPasswordList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = userList.get(i);
            userIdList.add(user.getUserID());
            userNameList.add(user.getUserName());
            userPasswordList.add(user.getPassword());
        }
        try {

            int len = userList.size();

            //mock all the music data
            when(mResultSetMock.next()).thenReturn(true, true, true, false);
            //User user = new User(rs.getString("userID"), rs.getString("userName"), rs.getString("password"));
            when(mResultSetMock.getString("userID")).thenReturn(userIdList.get(0), userIdList.subList(1, len).toArray(new String[len - 1]));
            when(mResultSetMock.getString("userName")).thenReturn(userNameList.get(0), userNameList.subList(1, len).toArray(new String[len - 1]));
            when(mResultSetMock.getString("password")).thenReturn(userPasswordList.get(0), userPasswordList.subList(1, len).toArray(new String[len - 1]));

            List<User> actualUserList = mActualUserManagementHSQLDB.getAllUsers();

            verify(mConnectionMock, times(1)).createStatement();
            // The executeQuery() method verifies that the PreparedStatement object is called once
            verify(statementMock, times(1)).executeQuery(anyString());
            verify(mResultSetMock, times(len)).getString("userID");
            verify(mResultSetMock, times(len)).getString("userName");
            verify(mResultSetMock, times(len)).getString("password");

            //Just compare the length of the data
            assertEquals(actualUserList.size(), len);
            for (int i = 0; i < len; i++) {
                User user = userList.get(i);
                User actualUser = actualUserList.get(i);
                assertEquals(user, actualUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testVerifyUser() {
        try {
            //mock all the user data
            when(mResultSetMock.next()).thenReturn(true,  false);
            //User user = new User(rs.getString("userID"), rs.getString("userName"), rs.getString("password"));
            when(mResultSetMock.getInt(1)).thenReturn(1);

            String userId = "testuser";
            String userPwd = "testpass";
            boolean flag = mActualUserManagementHSQLDB.verifyUser(userId, userPwd);

            verify(mConnectionMock, times(1)).prepareStatement(anyString());
            verify(mStatementMock).setString(eq(1), eq(userId));
            verify(mStatementMock).setString(eq(2), eq(userPwd));
            // The executeQuery() method verifies that the PreparedStatement object is called once
            verify(mStatementMock, times(1)).executeQuery();
            verify(mResultSetMock, times(1)).next();
            verify(mResultSetMock, times(1)).getInt(1);
            assertEquals(flag, true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
