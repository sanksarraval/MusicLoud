package com.example.musicloud.business;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.persistence.PlaylistPersistence;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AccessPlaylistTest {

    @Mock
    private PlaylistPersistence playlistPersistence;

    private AccessPlaylist accessPlaylist;

    @Before
    public void setUp() {
        playlistPersistence = mock(PlaylistPersistence.class);
        accessPlaylist = new AccessPlaylist(playlistPersistence);
    }

    @Test
    public void testGetPlaylists() {
        System.out.println("\n*********** Starting testGetPlaylists ***********");
        // Arrange
        List<Playlist> expectedPlaylists = Arrays.asList(new Playlist("Playlist 1"), new Playlist("Playlist 2"));
        when(playlistPersistence.getPlaylist()).thenReturn(expectedPlaylists);

        // Act
        List<Playlist> actualPlaylists = accessPlaylist.getPlaylists();

        // Assert
        assertEquals(expectedPlaylists, actualPlaylists);

        System.out.println("\n*********** Finished testGetPlaylists ***********");
    }

    @Test
    public void testInsertPlaylist() {

        System.out.println("\n*********** Starting testInsertPlaylist ***********");
        // Arrange
        Playlist playlistToInsert = new Playlist("Playlist 1");
        Playlist expectedPlaylist = new Playlist("Playlist 1");
        expectedPlaylist.setId(1);
        when(playlistPersistence.insertPlaylist(playlistToInsert)).thenReturn(expectedPlaylist);

        // Act
        Playlist actualPlaylist = accessPlaylist.insertPlaylist(playlistToInsert);

        // Assert
        assertEquals(expectedPlaylist, actualPlaylist);
        System.out.println("\n*********** Finished testInsertPlaylist ***********");
    }

    @Test
    public void testGetPlaylistId() {
        System.out.println("\n*********** Starting testGetPlaylistId ***********");
        // Arrange
        List<Playlist> playlists = Arrays.asList(new Playlist("Playlist 1"), new Playlist("Playlist 2"));
        when(playlistPersistence.getPlaylist()).thenReturn(playlists);

        // Act
        int actualId = accessPlaylist.getPlaylistId("Playlist 1");

        // Assert
        assertEquals(0, actualId);
        System.out.println("\n*********** Finished testGetPlaylistId ***********");
    }

    @Test
    public void testRemovePlaylist() {
        System.out.println("\n*********** Starting testRemovePlaylist ***********");
        // Arrange
        Playlist playlistToRemove = new Playlist("Playlist 1");

        // Act
        accessPlaylist.removePlaylist(playlistToRemove);

        // Assert
        verify(playlistPersistence).deletePlaylist(playlistToRemove);
        System.out.println("\n*********** Finished testRemovePlaylist ***********");
    }
}
