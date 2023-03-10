package com.example.musicloud.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.persistence.hsqldb.PlaylistPersistenceHSQLDB;

public class PlaylistPersistenceHSQLDBTest {
    private PlaylistPersistenceHSQLDB persistence;

    @Before
    public void setUp() {
        persistence = new PlaylistPersistenceHSQLDB("test.db");
    }

    @Test
    public void testGetPlaylist() {
        List<Playlist> playlists = persistence.getPlaylist();
        assertNotNull(playlists);
        assertTrue(playlists.size() > 0);
    }

    @Test
    public void testInsertPlaylist() {
        Playlist playlist = new Playlist(0, "Test Playlist", "This is a test playlist");
        Playlist result = persistence.insertPlaylist(playlist);
        assertNotNull(result);
        assertEquals(result.getPlaylistName(), playlist.getPlaylistName());
        assertEquals(result.getDescription(), playlist.getDescription());
    }

    @Test
    public void testDeletePlaylist() throws SQLException {
        List<Playlist> playlists = persistence.getPlaylist();
        assertTrue(playlists.size() > 0);
        Playlist playlist = playlists.get(0);
        persistence.deletePlaylist(playlist);
        List<Playlist> updatedPlaylists = persistence.getPlaylist();
        assertFalse(updatedPlaylists.contains(playlist));
    }
}
