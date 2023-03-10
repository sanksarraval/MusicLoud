package com.example.musicloud.persistence.hsqldb;
import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.SP;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SPPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SPPersistenceHSQLDB implements SPPersistence {
    private final String dbPath;

    public SPPersistenceHSQLDB (final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private SP fromResultSet (final ResultSet rs) throws SQLException {
        final int playlistId = rs.getInt("playlist_id");
        final String playlistName = rs.getString("playlist_name");
        final int songId = rs.getInt("song_id");
        final String songName = rs.getString("song_name");

        final Song song = new Song(songId);
        final Playlist playlist = new Playlist (playlistId);
        return new SP(song,playlist);
    }
    @Override
    public List<SP> getSP(String songName) {
        final List<SP> songPlaylists = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM playlist, songsPlaylists WHERE playlist.playlistId=songsPlaylists.playlist_id AND song_id = ?");
            st.setString(1, songName);

            final ResultSet rs = st.executeQuery();

            while (rs.next()){
                final SP record = fromResultSet(rs);
                songPlaylists.add(record);
            }

            rs.close();
            st.close();

        }
        catch (final SQLException e)
        {
            e.printStackTrace();
        }
        return songPlaylists;
    }

    @Override
    public List<SP> getPS(String playlistName) {
        final List<SP> songPlaylists = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM table_song, songsPlaylists WHERE table_song.song_id=songsPlaylists.song_id AND playlistId = ?");
            st.setString(1, playlistName);

            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final SP record = fromResultSet(rs);
                songPlaylists.add(record);
            }

            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return songPlaylists;
    }
}
