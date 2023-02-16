package com.example.musicloud.business;

import static org.junit.Assert.*;

import com.example.musicloud.persistence.stubs.SongPersistenceStub;
import com.example.musicloud.objects.Song;

import org.junit.Test;

public class SongListTest {
    
    @Test
    public void testDefaultVals()
    {
        SongPersistenceStub songs = new SongPersistenceStub();

        // Asserting songNames created by constructor
        assertEquals(songs.getSong(0).getSongName().equals("Faded"), true);
        assertEquals(songs.getSong(1).getSongName().equals("Don't Cry"),true);
        assertEquals(songs.getSong(2).getSongName().equals("That Girl"),true);
        assertEquals(songs.getSong(3).getSongName().equals("Normal No More"),true);
        assertEquals(songs.getSize(), 4);


        //asserting new values after insert
        songs.insertSong(new Song("Faded2", "Alan Walker"));
        assertEquals(songs.getSong(4).getSongName().equals("Faded2"), true);
    }

}
