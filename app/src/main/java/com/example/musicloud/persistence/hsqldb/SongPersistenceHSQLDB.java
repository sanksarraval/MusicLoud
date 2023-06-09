package com.example.musicloud.persistence.hsqldb;

import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SongPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongPersistenceHSQLDB implements SongPersistence {

    public final String TABLE_SONG = "table_song";
    public final String COLUMN_ID = "id";

    public final String COLUMN_SONG_NAME = "song_name";
    public final String COLUMN_ARTIST = "artist";
    public final String COLUMN_ALBUM_NAME = "album_name";
    public final String COLUMN_IS_LIKED = "is_liked";


    public SongPersistenceHSQLDB(final String dbPath) {
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

    @Override
    public List<Song> getAllSongs() {
        final List<Song> songList = new ArrayList<>();
        try {
            final Statement st = mConnection.createStatement();
            final ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s", TABLE_SONG));
            while (rs.next()) {
                final Song song = fromResultSet(rs);
                songList.add(song);
            }
            rs.close();
            st.close();
            return songList;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Song fromResultSet(final ResultSet rs) throws SQLException {
        return new Song(rs.getInt(COLUMN_ID), rs.getString(COLUMN_SONG_NAME), rs.getString(COLUMN_ARTIST), rs.getString(COLUMN_ALBUM_NAME), rs.getInt(COLUMN_IS_LIKED) == 1);
    }

    /**
     * gets the song based on its id
     *
     * @param id
     * @return song
     */
    @Override
    public Song getSong(int id) {
        String sql = "SELECT * FROM table_song WHERE id = ?";
        try {
            PreparedStatement stmt = mConnection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Song song = new Song(rs.getString("song_name"), rs.getString("artist"), rs.getString("album_name"));
                song.setId(rs.getInt("id"));
                return song;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets the size of the songlist
     *
     * @return
     */
    @Override
    public int getSize() {
        String sql = "SELECT COUNT(*) FROM table_song";
        try {
            PreparedStatement stmt = mConnection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * inserts a new song in the database
     *
     * @param currentSong
     * @return
     */
    @Override
    public Song insertSong(Song currentSong) {
        try {
            final PreparedStatement st = mConnection.prepareStatement(String.format("INSERT INTO %s(%s, %s, %s, %s)  VALUES(?, ?, ?, ?)", TABLE_SONG, COLUMN_SONG_NAME, COLUMN_ARTIST, COLUMN_ALBUM_NAME, COLUMN_IS_LIKED), Statement.RETURN_GENERATED_KEYS);
            st.setString(1, currentSong.getSongName());
            st.setString(2, currentSong.getArtist());
            st.setString(3, currentSong.getAlbumName());
            st.setInt(4, currentSong.isLiked() ? 1 : 0);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert data failure");
            }
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                //primary key ID
                int id = rs.getInt(COLUMN_ID);
                // Process the automatically generated primary key ID obtained
                currentSong.setId(id);
            }
            st.close();
            return currentSong;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * updates the song info in the database
     *
     * @param currentSong
     * @return
     */
    @Override
    public Song updateSong(Song currentSong) {
        try {
            final PreparedStatement st = mConnection.prepareStatement(String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?", TABLE_SONG, COLUMN_SONG_NAME, COLUMN_ARTIST, COLUMN_ALBUM_NAME, COLUMN_IS_LIKED, COLUMN_ID));
            st.setString(1, currentSong.getSongName());
            st.setString(2, currentSong.getArtist());
            st.setString(3, currentSong.getAlbumName());
            st.setInt(4, currentSong.isLiked() ? 1 : 0);
            st.setInt(5, currentSong.getId());
            st.executeUpdate();
            st.close();
            return currentSong;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * deletes a song in the database
     *
     * @param currentSong
     */
    @Override
    public void deleteSong(Song currentSong) {
        try {
            final PreparedStatement st = mConnection.prepareStatement(String.format("DELETE FROM %s WHERE %s = ?", TABLE_SONG, COLUMN_ID));
            st.setInt(1, currentSong.getId());
            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets all the song names in the database
     *
     * @return song names
     */
    @Override
    public List<String> allSongNames() {
        final List<String> songNameList = new ArrayList<>();
        try {
            final PreparedStatement st = mConnection.prepareStatement(String.format("SELECT %s FROM %s", COLUMN_SONG_NAME, TABLE_SONG));
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                songNameList.add(rs.getString(COLUMN_SONG_NAME));
            }
            rs.close();
            st.close();
            return songNameList;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * likes the song in the database and sets boolean to true
     *
     * @param currentSong
     */
    @Override
    public void likeSong(Song currentSong) {
        String sql = "UPDATE table_song SET is_liked = ? WHERE id = ?";
        try {
            PreparedStatement stmt = mConnection.prepareStatement(sql);
            currentSong.setLiked();
            stmt.setBoolean(1, true);
            stmt.setInt(2, currentSong.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * unlikes the song in the database and sets the boolean to false
     *
     * @param currentSong
     */
    @Override
    public void unlikeSong(Song currentSong) {
        String sql = "UPDATE table_song SET is_liked = ? WHERE song_name = ?";
        try {
            PreparedStatement stmt = mConnection.prepareStatement(sql);
            currentSong.setLiked();
            stmt.setBoolean(1, false);
            stmt.setString(2, currentSong.getSongName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if the song is liked or not in the database
     *
     * @param currentSong
     * @return if it's liked or not
     */
    @Override
    public boolean isLiked(Song currentSong) {
        return currentSong.isLiked();
    }

    /**
     * gets all the liked songs and returns a list of it
     *
     * @return all liked songs
     */
    @Override
    public List<Song> getLikedSongs() {
        List<Song> likedSongs = new ArrayList<>();
        String sql = "SELECT * FROM table_song WHERE is_liked = true ORDER BY artist, song_name";
        try {
            PreparedStatement stmt = mConnection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Song song = new Song(rs.getString("song_name"), rs.getString("artist"), rs.getString("album_name"), rs.getBoolean("is_liked"));
                song.setId(rs.getInt("id"));
                likedSongs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likedSongs;
    }

}
