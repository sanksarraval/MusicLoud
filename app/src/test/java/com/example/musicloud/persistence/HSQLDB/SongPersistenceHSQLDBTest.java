package com.example.musicloud.persistence.HSQLDB;

import com.example.musicloud.objects.Song;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SongPersistenceHSQLDBTest {

    private final String TABLE_SONG = "table_song";
    private final String COLUMN_ID = "id";
    private final String COLUMN_SONG_NAME = "song_name";
    private final String COLUMN_ARTIST = "artist";
    private final String COLUMN_ALBUM_NAME = "album_name";
    private final String COLUMN_IS_LIKED = "is_liked";

    private Connection c;


    @Before
    public void setUp() {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            File dbFile = new File("src/test/java/com/example/musicloud/app_db/SC");
            c = DriverManager.getConnection(String.format("jdbc:hsqldb:file:%s;shutdown=true", dbFile.getAbsolutePath()), "SA", "");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void insertSong() {
        try {
            final PreparedStatement st = c.prepareStatement(String.format("INSERT INTO %s(%s, %s, %s, %s)  VALUES(?, ?, ?, ?)", TABLE_SONG, COLUMN_SONG_NAME, COLUMN_ARTIST, COLUMN_ALBUM_NAME, COLUMN_IS_LIKED));
            st.setString(1, "test");
            st.setString(2, "test");
            st.setString(3, "test");
            st.setInt(4, 0);
            st.executeUpdate();
            st.close();
            getLastSong();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getLastSong() throws SQLException {
        int id = -1;
        final Statement stQuery = c.createStatement();
        final ResultSet rs = stQuery.executeQuery(String.format("SELECT * FROM %s order by id desc", TABLE_SONG));
        if (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            Song song = new Song(id, rs.getString(COLUMN_SONG_NAME), rs.getString(COLUMN_ARTIST), rs.getString(COLUMN_ALBUM_NAME), rs.getInt(COLUMN_IS_LIKED) == 1);
            System.out.println("song==" + song);
        }
        rs.close();
        stQuery.close();
        return id;
    }

    @Test
    public void getAllSong() {
        try {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s", TABLE_SONG));
            while (rs.next()) {
                Song song = new Song(rs.getInt(COLUMN_ID), rs.getString(COLUMN_SONG_NAME), rs.getString(COLUMN_ARTIST), rs.getString(COLUMN_ALBUM_NAME), rs.getInt(COLUMN_IS_LIKED) == 1);
                System.out.println("song==" + song);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void updateSong() {
        try {
            final PreparedStatement st = c.prepareStatement(String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?", TABLE_SONG, COLUMN_SONG_NAME, COLUMN_ARTIST, COLUMN_ALBUM_NAME, COLUMN_IS_LIKED));
            st.setString(1, "test_111");
            st.setString(2, "test_111");
            st.setString(3, "test_111");
            st.setInt(4, 1);
            st.executeUpdate();
            st.close();
            getLastSong();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteSong() {
        try {
            int lastId = getLastSong();
            System.out.println("lastId==" + lastId);
            final PreparedStatement st = c.prepareStatement(String.format("DELETE FROM %s WHERE %s = ?", TABLE_SONG, COLUMN_ID));
            st.setInt(1, lastId);
            st.executeUpdate();
            st.close();
            System.out.println("2222==lastId==" + getLastSong());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllSongName() {
        try {
            final PreparedStatement st = c.prepareStatement(String.format("SELECT %s FROM %s", COLUMN_SONG_NAME, TABLE_SONG));
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("songName==" + rs.getString(COLUMN_SONG_NAME));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void likeSong(){

    }

}
