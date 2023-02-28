package com.example.musicloud.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaylistTest {
    @Test
    public void testPlaylist1(){
        Playlist playlist;
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give"));
        songs.add(new Song("Rain Man", "Ketsa", "Ketsa - Rain Man"));
        System.out.println("\n Starting testPlaylist");

        playlist = new Playlist("name", "description", songs);
        assertNotNull(playlist);
        assertEquals("name", playlist.getPlaylistName());
        assertEquals("description", playlist.getDescription());
        assertEquals(songs, playlist.getSongList());

        System.out.println("Finished testPlaylist");
    }
}
