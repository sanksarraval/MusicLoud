package com.example.musicloud.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.musicloud.objects.Song;

import org.junit.Test;

public class AccessSongsTest {
    @Test
    public void testAccessPlaylist (){
        AccessSongs accessSongs;
        Song songs;
        System.out.println ("\n Starting testAccessSongs");

        accessSongs = new AccessSongs();
        songs = new Song("song5", "artist of song", "album");
        assertNotNull(accessSongs);
        assertEquals(songs, accessSongs.insertSong(songs));
        System.out.println(accessSongs.getSongs());

        System.out.println("Finished testAccessSongs");
    }
}
