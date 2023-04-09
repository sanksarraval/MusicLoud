package com.example.musicloud.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SongTest {
    private Song song, song1, song2, song3,song4;

    @Before
    public void setUp() {

        song = new Song("song","artist");
        song1 = new Song("song1","artist1","album1");
        song2 = new Song(102,"song2","artist2","album2", true);
        song3 = new Song(103,"1");
        song4 = new Song ("song4", "artist4","album4", true);
    }

    @Test
    public void testSongConstructor(){
        System.out.println("\n*********** Starting testSongConstructor ***********");
        assertNotNull(song);
        assertNotNull(song1);
        assertNotNull(song2);
        assertNotNull(song3);
        assertNotNull(song4);
        System.out.println("*********** Finished testSongConstructor ***********");
    }

    @Test
    public void testGetSongName() {
        System.out.println("\n*********** Starting testGetSongName ***********");
        assertEquals("song", song.getSongName());
        assertEquals("song1", song1.getSongName());
        assertEquals("song2", song2.getSongName());
        assertEquals("song4", song4.getSongName());
        System.out.println("\n*********** Testing song with null Name ***********");
        assertNull(song3.getSongName());
        System.out.println("*********** Finished testGetSongName ***********");

    }

    @Test
    public void testGetSongID()
    {
        System.out.println("\n*********** Starting testGetSongID ***********");

        assertEquals(102, song2.getId());
        assertEquals(103, song3.getId());

        System.out.println("*********** Finished testGetSongID ***********");
    }

    @Test
    public void testGetSongIDEmptyID()
    {
        System.out.println("\n*********** Starting testGetSongIDEmptyID ***********");

        assertEquals(0, song.getId());
        assertEquals(0, song1.getId());
        assertEquals(0, song4.getId());

        System.out.println("*********** Finished testGetSongIDEmptyID ***********");
    }

    @Test
    public void testSetID(){
        System.out.println("\n*********** Starting testSetID ***********");
        song.setId(100);
        song1.setId(101);
        song4.setId(104);
        assertEquals(100, song.getId());
        assertEquals(101, song1.getId());
        assertEquals(104, song4.getId());

        System.out.println("*********** Finished testSetID ***********");

    }

    @Test
    public void testGetArtist() {
        System.out.println("\n*********** Starting testGetArtist ***********");
        assertEquals("artist", song.getArtist());
        assertEquals("artist1", song1.getArtist());
        assertEquals("artist2", song2.getArtist());
        assertEquals("artist4", song4.getArtist());
        System.out.println("*********** Testing song with null artist ***********");
        assertNull(song3.getArtist());
        System.out.println("*********** Finished testGetArtist ***********");

    }

    @Test
    public void testGetAlbumName() {
        System.out.println("\n*********** Starting testGetAlbumName ***********");
        assertEquals("album1", song1.getAlbumName());
        assertEquals("album2", song2.getAlbumName());
        assertEquals("album4", song4.getAlbumName());
        System.out.println("*********** Testing song with no album name ***********");
        assertEquals("na", song.getAlbumName());
        assertEquals("na", song3.getAlbumName());
        System.out.println("*********** Finished testGetArtist ***********");

    }

    @Test
    public void testIsLiked() {
        System.out.println("\n*********** Starting testIsLiked ***********");
        assertFalse(song.isLiked());
        assertFalse(song1.isLiked());
        assertTrue(song2.isLiked());
        assertFalse(song3.isLiked());
        assertTrue(song4.isLiked());
        System.out.println("*********** Finished testIsLiked ***********");

    }

    @Test
    public void testSetLike() {
        System.out.println("\n*********** Starting testSetLike ***********");
        song.setLiked();
        song1.setLiked();
        song2.setLiked();
        song3.setLiked();
        song4.setLiked();

        assertTrue(song.isLiked());
        assertTrue(song1.isLiked());
        assertFalse(song2.isLiked());
        assertTrue(song3.isLiked());
        assertFalse(song4.isLiked());
        System.out.println("*********** Finished testSetLike ***********");
    }

    @Test
    public void testSongToString()
    {
        System.out.println("\n*********** Starting testPlaylistToString ***********");

        String expected = "Song{songName='song', artist='artist', album name='na', liked='false'}";
        assertEquals(expected, song.toString());

        expected = "Song{songName='song1', artist='artist1', album name='album1', liked='false'}";
        assertEquals(expected, song1.toString());

        expected = "Song{id ='102', songName='song2', artist='artist2', album name='album2', liked='true'}";
        assertEquals(expected, song2.toString());

        expected = "Song{id ='103', album name='na', liked='false'}";
        assertEquals(expected, song3.toString());
        System.out.println("*********** Finished testPlaylistToString ***********");
    }
}