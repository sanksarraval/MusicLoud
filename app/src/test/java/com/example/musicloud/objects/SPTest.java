package com.example.musicloud.objects;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SPTest {
    private Playlist playlist, playlist1;
    private Song song, song1, song2, song3;

    private SP sp, sp1, sp2, sp3;

    @Before
    public void setUp() {

        playlist = new Playlist(100,"playlist name","this is the description of playlist");
        playlist1 = new Playlist(101,"playlist1 name","this is the description of playlist1");

        song = new Song ("song", "artist");
        song1 = new Song ("song1", "artist1");
        song2 = new Song ("song2", "artist2");
        song3 = new Song ("song3", "artist3");

        sp = new SP(song, playlist);
        sp1 = new SP(song1, playlist);
        sp2 = new SP(song2, playlist);
        sp3 = new SP(song3, playlist1);
    }

    @Test public void testSPConstructor(){
        System.out.println("\n*********** Starting testSPConstructor ***********");
        assertNotNull(sp);
        assertNotNull(sp1);
        assertNotNull(sp2);
        assertNotNull(sp3);
        System.out.println("*********** Finished testSPConstructor ***********");
    }

    @Test
    public void testGetSongID() {
        System.out.println("\n*********** Starting testGetSongID ***********");
        assertEquals(song.getId(), sp.getSongID());
        assertEquals(song1.getId(), sp1.getSongID());
        assertEquals(song2.getId(), sp2.getSongID());
        assertEquals(song3.getId(), sp3.getSongID());
        System.out.println("*********** Finished testGetSongID ***********");

    }
    @Test
    public void testGetSongName() {
        System.out.println("\n*********** Starting testGetSongName ***********");
        assertEquals(song.getSongName(), sp.getSongName());
        assertEquals(song1.getSongName(), sp1.getSongName());
        assertEquals(song2.getSongName(), sp2.getSongName());
        assertEquals(song3.getSongName(), sp3.getSongName());
        System.out.println("*********** Finished testGetSongName ***********");

    }
    @Test
    public void testGetPlaylistName() {
        System.out.println("\n*********** Starting testGetPlaylistName ***********");
        assertEquals(playlist.getPlaylistName(), sp.getPlaylistName());
        assertEquals(playlist.getPlaylistName(), sp1.getPlaylistName());
        assertEquals(playlist1.getPlaylistName(), sp3.getPlaylistName());
        System.out.println("*********** Finished testGetPlaylistName ***********");

    }

    @Test
    public void testGetPlaylistID() {
        System.out.println("\n*********** Starting testGetPlaylistID ***********");
        assertEquals(playlist.getId(), sp.getPlaylistId());
        assertEquals(playlist.getId(), sp1.getPlaylistId());
        assertEquals(playlist1.getId(), sp3.getPlaylistId());
        System.out.println("*********** Finished testGetPlaylistID ***********");
    }

    @Test
    public void testSPtoString()
    {
        System.out.println("\n*********** Starting testSPtoString ***********");

        String expected = "SP: \nSong: song1\nPlaylist: playlist name";
        assertEquals(expected, sp1.toString());
        String expected1 = "SP: \nSong: song3\nPlaylist: playlist1 name";
        assertEquals(expected1, sp3.toString());

        System.out.println("*********** Finished testSPtoString ***********");
    }
}
