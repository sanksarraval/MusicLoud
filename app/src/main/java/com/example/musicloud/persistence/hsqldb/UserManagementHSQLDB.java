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

    public UserManagementHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM USERS")) {
            while (rs.next()) {
                User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("userName"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUser(String userID) {
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE userID = ?")) {
            stmt.setString(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("userID"), rs.getString("password"), rs.getString("userName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verifyUser(String userID, String password) {
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM USERS WHERE userID = ? AND password = ?")) {
            stmt.setString(1, userID);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User addAccount(User newUser) {
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO USERS (userID, password, userName) VALUES (?, ?, ?)")) {
            stmt.setString(1, newUser.getUserID());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getUserName());
            stmt.executeUpdate();
            return newUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}






