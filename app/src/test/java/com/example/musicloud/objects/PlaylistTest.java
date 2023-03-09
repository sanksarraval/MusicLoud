package com.example.musicloud.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PlaylistTest {
    private Playlist playlist, playlist1, playlist2, playlist3;

    @Before
    public void setUp() {

        playlist = new Playlist(100,"playlist name","this is the description of playlist");
        playlist1 = new Playlist(101,"playlist1 name","this is the description of playlist1");
        playlist2 = new Playlist(102,"playlist2 name","this is the description of playlist2");
        playlist3 = new Playlist(103,"playlist3 name","this is the description of playlist3");

    }

    @Test public void testPlaylistConstructor(){
        System.out.println("\n*********** Starting testPlaylistConstructor ***********");
        assertNotNull(playlist);
        assertNotNull(playlist1);
        assertNotNull(playlist2);
        assertNotNull(playlist3);
        System.out.println("*********** Finished testPlaylistConstructor ***********");
    }

    @Test
    public void testGetPlaylistName() {
        System.out.println("\n*********** Starting testGetPlaylistName ***********");
        assertEquals("playlist name", playlist.getPlaylistName());
        assertEquals("playlist1 name", playlist1.getPlaylistName());
        assertEquals("playlist2 name", playlist2.getPlaylistName());
        assertEquals("playlist3 name", playlist3.getPlaylistName());
        System.out.println("*********** Finished testGetPlaylistName ***********");

    }

    @Test
    public void testGetPlaylistNameWithEmptyName() {
        System.out.println("\n*********** Starting testGetPlaylistNameWithEmptyName ***********");

        playlist.setPlaylistName("");
        assertEquals("", playlist.getPlaylistName());

        System.out.println("*********** Finished testGetPlaylistNameWithEmptyName ***********");
    }

    @Test
    public void testGetPlaylistIDReturnsCorrectPlaylistID()
    {
        System.out.println("\n*********** Starting testGetPlaylistIDReturnsCorrectPlaylistID ***********");

        assertEquals(100, playlist.getId());
        assertEquals(101, playlist1.getId());
        assertEquals(102, playlist2.getId());
        assertEquals(103, playlist3.getId());

        System.out.println("*********** Finished testGetPlaylistIDReturnsCorrectPlaylistID ***********");
    }


    @Test
    public void testGetPlaylistDescription()
    {

        System.out.println("\n*********** Starting testGetPlaylistDescription ***********");

        assertEquals("this is the description of playlist", playlist.getDescription());
        assertEquals("this is the description of playlist1", playlist1.getDescription());
        assertEquals("this is the description of playlist2", playlist2.getDescription());
        assertEquals("this is the description of playlist3", playlist3.getDescription());
        System.out.println("*********** Finished testGetPlaylistDescription ***********");
    }

    @Test
    public void testSetPlaylistName()
    {
        System.out.println("\n*********** Starting testSetPlaylistName ***********");

        playlist.setPlaylistName("bops");
        playlist1.setPlaylistName("hard rock");
        playlist2.setPlaylistName("sleepy music");
        playlist3.setPlaylistName("driving jams");

        assertEquals("bops", playlist.getPlaylistName());
        assertEquals("hard rock", playlist1.getPlaylistName());
        assertEquals("sleepy music", playlist2.getPlaylistName());
        assertEquals("driving jams", playlist3.getPlaylistName());

        System.out.println("*********** Finished testSetPlaylistName ***********");
    }

    @Test
    public void testSetPlaylistID()
    {
        System.out.println("\n*********** Starting testSetPlaylistID ***********");
        playlist.setId(100);
        playlist1.setId(200);
        playlist2.setId(300);
        playlist3.setId(400);

        assertEquals(100, playlist.getId());
        assertEquals(200, playlist1.getId());
        assertEquals(300, playlist2.getId());
        assertEquals(400, playlist3.getId());

        System.out.println("*********** Finished testSetPlaylistID ***********");
    }

    @Test
    public void testSetPlaylistDescription()
    {
        System.out.println("\n*********** Starting testSetPlaylistDescription ***********");

        // Setting description
        playlist.setDescription("password");
        playlist1.setDescription("password1");
        playlist2.setDescription("password2");
        playlist3.setDescription("password3");


        assertEquals("password", playlist.getDescription());
        assertEquals("password1", playlist1.getDescription());
        assertEquals("password2", playlist2.getDescription());
        assertEquals("password3", playlist3.getDescription());
        System.out.println("*********** Finished testSetPlaylistDescription ***********");
    }
    @Test
    public void testPlaylistEquals()
    {
        System.out.println("\n*********** Starting testPlaylistEquals ***********");
        assertTrue(playlist.equals((Object)playlist));
        assertFalse(playlist1.equals((Object)7));
        assertFalse(playlist2.equals((Object)playlist3));
        System.out.println("*********** Finished testPlaylistEquals ***********");
    }

    @Test
    public void testPlaylistToString()
    {
        System.out.println("\n*********** Starting testPlaylistToString ***********");

        String expected = "\nPlaylist: \n id: 101 \n name: playlist1 name \n description: this is the description of playlist1";
        assertEquals(expected, playlist1.toString());
        String expected1 = "\nPlaylist: \n id: 103 \n name: playlist3 name \n description: this is the description of playlist3";
        assertEquals(expected1, playlist3.toString());

        System.out.println("*********** Finished testPlaylistToString ***********");
    }
}
