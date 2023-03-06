package com.example.musicloud.persistence.hsqldb;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.persistence.PlaylistPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistHSQLDB implements PlaylistPersistence {
    private final String dbPath;

    public PlaylistHSQLDB(String dbPath) {
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
    private void createTable() throws SQLException {
        final Connection conn = connection();
        String query = "CREATE TABLE IF NOT EXISTS Users (UserID VARCHAR(50) PRIMARY KEY, UserName VARCHAR(50), Password VARCHAR(50))";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }
    private void insertDummyData() throws SQLException {
        final Connection conn = connection();
        String query1 = "INSERT INTO Playlists VALUES('name', 'description')";
        String query2 = "INSERT INTO Playlists VALUES('good playlist', 'this playlist is great')";
        String query3 = "INSERT INTO Playlists VALUES('bad playlist', 'this playlist sucks')";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query1);
        stmt.executeUpdate(query2);
        stmt.executeUpdate(query3);
        stmt.close();
    }
    @Override
    public List<Playlist> getPlaylist() {
        List<Playlist> playlists = new ArrayList<>();
        try (final Connection conn = connection();
             final Statement stmt = conn.createStatement();
             final ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYLISTS")) {
            while (rs.next()) {
                Playlist playlist = new Playlist(rs.getString("playlistName"), rs.getString("description"));
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    @Override
    public Playlist insertPlaylist(Playlist currentPlaylist) {
        try (final Connection conn = connection();
             final PreparedStatement stmt = conn.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)")) {
            stmt.setString(1, currentPlaylist.getPlaylistName());
            stmt.setString(2, currentPlaylist.getDescription());
            stmt.executeUpdate();
            return currentPlaylist;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return currentPlaylist;
    }

    @Override
    public Playlist updatePlaylist(Playlist currentPlaylist) {
        return null;
    }

    @Override
    public void deletePlaylist(Playlist currentPlaylist) {

    }
}






