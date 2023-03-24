package com.example.musicloud.persistence.HSQLDB;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.hsqldb.SongPersistenceHSQLDB;

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
public class SongPersistenceHSQLDBTest {

    @Mock
    private Connection mConnectionMock;
    @Mock
    private PreparedStatement mStatementMock;
    @Mock
    private ResultSet mResultSetMock;
    private SongPersistenceHSQLDB mActualSongPersistenceHSQLDB;

    @Before
    public void setUp() {
        /*
         * The virtual database connection object and the PreparedStatement object are bound to the Mockito framework
         * using the when() and thenReturn() methods
         */
        mActualSongPersistenceHSQLDB = new SongPersistenceHSQLDB("src/test/HSQLDB/app_db/SC");
        // Assign a simulated connection to a db object
        mActualSongPersistenceHSQLDB.setConnection(mConnectionMock);
        // Returns the prepareStatement() method of the simulated object to the simulated PreparedStatement object
        try {
            when(mConnectionMock.prepareStatement(anyString())).thenReturn(mStatementMock);
            when(mConnectionMock.prepareStatement(anyString(), anyInt())).thenReturn(mStatementMock);
            when(mStatementMock.executeQuery()).thenReturn(mResultSetMock);
            when(mStatementMock.getGeneratedKeys()).thenReturn(mResultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetAllSongs() {
        // Returns the prepareStatement() method of the simulated object to the simulated PreparedStatement object
        Statement statementMock = mock(Statement.class);
        try {
            when(mConnectionMock.createStatement()).thenReturn(statementMock);
            when(statementMock.executeQuery(anyString())).thenReturn(mResultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Simulate the expected  data
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Rain Man", "Ketsa", "Ketsa - Rain Man", false));
        songList.add(new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give", false));
        songList.add(new Song("Nightfall", "Stereohada", "Stereohada - Nightfall", false));
        List<Integer> songIdList = new ArrayList<>();
        List<String> songNameList = new ArrayList<>();
        List<String> songArtistList = new ArrayList<>();
        List<String> songAlbumNameList = new ArrayList<>();
        List<Integer> songLikedList = new ArrayList<>();
        for (int i = 100; i < 100 + songList.size(); i++) {
            Song song = songList.get(i - 100);
            songIdList.add(song.getId());
            songNameList.add(song.getSongName());
            songArtistList.add(song.getArtist());
            songAlbumNameList.add(song.getAlbumName());
            songLikedList.add(song.getId());
        }

        try {

            int len = songList.size();

            //mock all the music data
            when(mResultSetMock.next()).thenReturn(true, true, true, false);
            when(mResultSetMock.getInt(mActualSongPersistenceHSQLDB.COLUMN_ID)).thenReturn(songIdList.get(0), songIdList.subList(1, len).toArray(new Integer[len - 1]));
            when(mResultSetMock.getString(mActualSongPersistenceHSQLDB.COLUMN_SONG_NAME)).thenReturn(songNameList.get(0), songNameList.subList(1, len).toArray(new String[len - 1]));
            when(mResultSetMock.getString(mActualSongPersistenceHSQLDB.COLUMN_ARTIST)).thenReturn(songArtistList.get(0), songArtistList.subList(1, len).toArray(new String[len - 1]));
            when(mResultSetMock.getString(mActualSongPersistenceHSQLDB.COLUMN_ALBUM_NAME)).thenReturn(songAlbumNameList.get(0), songAlbumNameList.subList(1, len).toArray(new String[len - 1]));
            when(mResultSetMock.getInt(mActualSongPersistenceHSQLDB.COLUMN_IS_LIKED)).thenReturn(songLikedList.get(0), songLikedList.subList(1, len).toArray(new Integer[len - 1]));

            List<Song> actualSongList = mActualSongPersistenceHSQLDB.getAllSongs();

            verify(mConnectionMock, times(1)).createStatement();
            // The executeQuery() method verifies that the PreparedStatement object is called once
            verify(statementMock, times(1)).executeQuery(anyString());
            verify(mResultSetMock, times(len)).getInt(mActualSongPersistenceHSQLDB.COLUMN_ID);
            verify(mResultSetMock, times(len)).getString(mActualSongPersistenceHSQLDB.COLUMN_SONG_NAME);
            verify(mResultSetMock, times(len)).getString(mActualSongPersistenceHSQLDB.COLUMN_ARTIST);
            verify(mResultSetMock, times(len)).getString(mActualSongPersistenceHSQLDB.COLUMN_ALBUM_NAME);
            verify(mResultSetMock, times(len)).getInt(mActualSongPersistenceHSQLDB.COLUMN_IS_LIKED);

            /*
             *We started to add music data by default, but because our song data table is auto-increment with id primary key, we cannot simply compare the whole object,
             * so we need to loop the comparison of name, artist, etc
             */

            //Just compare the length of the data
            assertEquals(actualSongList.size(), songList.size());
            for (int i = 0; i < songList.size(); i++) {
                Song mockSong = songList.get(i);
                Song actualSong = actualSongList.get(i);
                assertEquals(actualSong.getId(), mockSong.getId());
                assertEquals(actualSong.getSongName(), mockSong.getSongName());
                assertEquals(actualSong.getArtist(), mockSong.getArtist());
                assertEquals(actualSong.getAlbumName(), mockSong.getAlbumName());
                assertEquals(actualSong.isLiked(), mockSong.isLiked());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInsertSong() {
        try {
            //Here, we re-mock because we need to get the primary key
            when(mConnectionMock.prepareStatement(anyString(), anyInt())).thenReturn(mStatementMock);
            when(mStatementMock.executeUpdate()).thenReturn(1);
            when(mStatementMock.getGeneratedKeys()).thenReturn(mResultSetMock);

            //mock all the music data
            when(mResultSetMock.next()).thenReturn(true);
            when(mResultSetMock.getInt(mActualSongPersistenceHSQLDB.COLUMN_ID)).thenReturn(100);

            Song song = new Song("Test-Name", "Test-Artist", "Test-Album-Name", true);
            song = mActualSongPersistenceHSQLDB.insertSong(song);

            verify(mConnectionMock, times(1)).prepareStatement(anyString(), anyInt());
            verify(mStatementMock).setString(eq(1), eq("Test-Name"));
            verify(mStatementMock).setString(eq(2), eq("Test-Artist"));
            verify(mStatementMock).setString(eq(3), eq("Test-Album-Name"));
            verify(mStatementMock).setInt(eq(4), eq(1));
            // The executeUpdate() method verifies that the PreparedStatement object is called once
            verify(mStatementMock, times(1)).executeUpdate();
            verify(mResultSetMock, times(1)).getInt(mActualSongPersistenceHSQLDB.COLUMN_ID);
            assertEquals(100, song.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateSong() {
        try {
            Song song = new Song(100, "Test-Name", "Test-Artist-update", "Test-Album-Name-update", true);
            mActualSongPersistenceHSQLDB.updateSong(song);
            verify(mConnectionMock, times(1)).prepareStatement(anyString());
            verify(mStatementMock).setString(eq(1), eq("Test-Name"));
            verify(mStatementMock).setString(eq(2), eq("Test-Artist-update"));
            verify(mStatementMock).setString(eq(3), eq("Test-Album-Name-update"));
            verify(mStatementMock).setInt(eq(4), eq(1));
            verify(mStatementMock).setInt(eq(5), eq(100));
            // The executeUpdate() method verifies that the PreparedStatement object is called once
            verify(mStatementMock, times(1)).executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeleteSong() {
        try {
            Song song = new Song(100);
            mActualSongPersistenceHSQLDB.deleteSong(song);
            verify(mConnectionMock, times(1)).prepareStatement(anyString());
            verify(mStatementMock).setInt(eq(1), eq(100));
            // The executeUpdate() method verifies that the PreparedStatement object is called once
            verify(mStatementMock, times(1)).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllSongName() {
        try {
            //Here we're simulating three pieces of music data, so we need to write three true's here
            List<String> songNameList = new ArrayList<>();
            songNameList.add("Rain Man");
            songNameList.add("Not Enough To Give");
            songNameList.add("Nightfall");
            when(mResultSetMock.next()).thenReturn(true, true, true, false);
            int len = songNameList.size();
            when(mResultSetMock.getString(mActualSongPersistenceHSQLDB.COLUMN_SONG_NAME)).thenReturn(songNameList.get(0), songNameList.subList(1, len).toArray(new String[len - 1]));

            List<String> nameList = mActualSongPersistenceHSQLDB.allSongNames();

            verify(mConnectionMock, times(1)).prepareStatement(anyString());
            // The executeQuery() method verifies that the PreparedStatement object is called once
            verify(mStatementMock, times(1)).executeQuery();
            verify(mResultSetMock, times(len)).getString(mActualSongPersistenceHSQLDB.COLUMN_SONG_NAME);

            assertEquals(songNameList, nameList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void likeSong() {

    }

}
