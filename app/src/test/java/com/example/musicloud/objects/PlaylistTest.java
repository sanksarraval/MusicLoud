package com.example.musicloud.objects;

import static org.junit.Assert.*;

import org.junit.Test;


public class PlaylistTest {
    @Test
    public void testPlaylist1(){
        Playlist playlist;
        System.out.println("\n Starting testPlaylist");

        playlist = new Playlist("name", "description");
        assertNotNull(playlist);
        assertEquals("name", playlist.getPlaylistName());
        assertEquals("description", playlist.getDescription());

        System.out.println("Finished testPlaylist");
    }
}
