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

    private final String dbPath;
    /**
     * UserManagementHSQLDB Constructor
     * @param dbPath: Inject the dbPath
     * */
    public UserManagementHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        try{
            createTable();
            insertDummyData();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }
    /**
     * createTable: Creates a Table if not created already.
     *
     * */
    private void createTable() throws SQLException {
        final Connection conn = connection();
        String query = "CREATE TABLE IF NOT EXISTS Users (UserID VARCHAR(50) PRIMARY KEY, UserName VARCHAR(50), Password VARCHAR(50))";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }
    /**
     * insertDummyData: Inserting the Dummy Data
     *
     * */
    private void insertDummyData() throws SQLException {
        final Connection conn = connection();
        String query1 = "INSERT INTO Users VALUES('ravals1', 'Sanskar Raval', 'Sanskar123')";
        String query2 = "INSERT INTO Users VALUES('chauhana', 'Aakash Chaohan', 'Aakash123')";
        String query3 = "INSERT INTO Users VALUES('seraspij', 'Jacob Seraspi', 'Jacob123')";
        String query4 = "INSERT INTO Users VALUES('admin', 'admin', 'admin')";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query1);
        stmt.executeUpdate(query2);
        stmt.executeUpdate(query3);
        stmt.executeUpdate(query4);
        stmt.close();
    }

    /**
     * getAllUsers: Returns a list of all users
     * @return: List<User>
     * */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (final Connection conn = connection();
             final Statement stmt = conn.createStatement();
             final ResultSet rs = stmt.executeQuery("SELECT * FROM USERS")) {
            while (rs.next()) {
                User user = new User(rs.getString("userID"), rs.getString("password"), rs.getString("userName"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    /**
     * getUser: Returns user
     * @return: User
     * */
    @Override
    public User getUser(String userID) {
        try (final Connection conn = connection();
             final PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE userID = ?")) {
            stmt.setString(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("userID"), rs.getString("password"), rs.getString("userName"));
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * verifyUser: Verifies the user.
     * @return: Boolean, true if the user is valid and already registered.
     * */
    @Override
    public boolean verifyUser(String userID, String password) {
        boolean flag = false;
        try (final Connection conn = connection();
             final PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM USERS WHERE userID = ? AND password = ?")) {
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
     * @return: User
     * */
    @Override
    public User addAccount(User newUser) {
        try (final Connection conn = connection();
             final PreparedStatement stmt = conn.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)")) {
            stmt.setString(1, newUser.getUserID());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getUserName());
            stmt.executeUpdate();
            return newUser;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * cleanTable: Cleans the database for Testing.
     * */
    public void cleanTable() {
        try (final Connection conn = connection();
             final Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM USERS");
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * dropTable: Drops the database for Testing.
     * */
    public void dropTable() {
        try (final Connection conn = connection();
             final Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS USERS");
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}






