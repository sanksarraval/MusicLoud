package com.example.musicloud.persistence.hsqldb;

import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.UserManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManagementHSQLDB implements UserManagement {

    private static final String TAG = "UserManagementHSQLDB";

    /**
     * UserManagementHSQLDB Constructor
     *
     * @param dbPath: Inject the dbPath
     */
    public UserManagementHSQLDB(final String dbPath) {
        try {
            this.mConnection = DriverManager.getConnection(String.format("jdbc:hsqldb:file:%s;shutdown=true", dbPath), "SA", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection mConnection;

    public void setConnection(Connection connection) {
        this.mConnection = connection;
    }

    /**
     * getAllUsers: Returns a list of all users
     *
     * @return: List<User>
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (final Statement stmt = mConnection.createStatement();
             final ResultSet rs = stmt.executeQuery("SELECT * FROM USERS")) {
            while (rs.next()) {
                User user = new User(rs.getString("userID"), rs.getString("userName"), rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * getUser: Returns user
     *
     * @return: User
     */
    @Override
    public User getUser(String userID) {
        try (final PreparedStatement stmt = mConnection.prepareStatement("SELECT * FROM USERS WHERE userID = ?")) {
            stmt.setString(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("userID"), rs.getString("userName"), rs.getString("password"));
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * verifyUser: Verifies the user.
     *
     * @return: Boolean, true if the user is valid and already registered.
     */
    @Override
    public boolean verifyUser(String userID, String password) {
        boolean flag = false;
        try (final PreparedStatement stmt = mConnection.prepareStatement("SELECT COUNT(*) FROM USERS WHERE userID = ? AND password = ?")) {
            stmt.setString(1, userID);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 1;
                }
            }

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * addAccount: Adds an account into the database.
     *
     * @return: User
     */
    @Override
    public User addAccount(User newUser) {
        try (final PreparedStatement stmt = mConnection.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)")) {
            stmt.setString(1, newUser.getUserID());
            stmt.setString(2, newUser.getUserName());
            stmt.setString(3, newUser.getPassword());
            stmt.executeUpdate();
            return newUser;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * cleanTable: Cleans the database for Testing.
     */
    public void cleanTable() {
        try (final Statement stmt = mConnection.createStatement()) {
            stmt.executeUpdate("DELETE FROM USERS");
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * dropTable: Drops the database for Testing.
     */
    public void dropTable() {
        try (final Statement stmt = mConnection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS USERS");
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}






