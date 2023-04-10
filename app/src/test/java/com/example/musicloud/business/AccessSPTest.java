package com.example.musicloud.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.SP;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.PlaylistPersistence;
import com.example.musicloud.persistence.SPPersistence;
import com.example.musicloud.stubs.SPPersistenceStub;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class AccessSPTest {

    @Mock
    private SPPersistence dataAccess;

    private AccessSP accessSP;

    @Before
    public void setUp() {
        dataAccess = mock(SPPersistence.class);
        accessSP = new AccessSP(dataAccess);
    }

    @Test
    public void testGetSP() {
        System.out.println("\n*********** Starting testGetSP ***********");
        // arrange
        String songName = "song1";
        SP sp1 = mock(SP.class);
        SP sp2 = mock(SP.class);
        List<SP> spList = new ArrayList<>();
        spList.add(sp1);
        spList.add(sp2);
        when(dataAccess.getSP(songName)).thenReturn(spList);

        // act
        SP result1 = accessSP.getSP(songName);
        SP result2 = accessSP.getSP(songName);
        SP result3 = accessSP.getSP(songName);

        // assert
        assertEquals(sp1, result1);
        assertEquals(sp2, result2);
        assertEquals(null, result3);
        System.out.println("\n*********** Finished testGetSP ***********");
    }

    @Test
    public void testAllSongs() {
        System.out.println("\n*********** Starting testAllSongs ***********");
        // arrange
        String playlistName = "playlist1";
        Song song1 = mock(Song.class);
        Song song2 = mock(Song.class);
        SP sp1 = mock(SP.class);
        SP sp2 = mock(SP.class);
        when(sp1.getSong()).thenReturn(song1);
        when(sp2.getSong()).thenReturn(song2);
        List<SP> spList = new ArrayList<>();
        spList.add(sp1);
        spList.add(sp2);
        when(dataAccess.getPS(playlistName)).thenReturn(spList);

        // act
        List<Song> result = accessSP.allSongs(playlistName);

        // assert
        assertEquals(2, result.size());
        assertEquals(song1, result.get(0));
        assertEquals(song2, result.get(1));
        System.out.println("\n*********** Finished testAllSongs ***********");
    }

    @Test
    public void testRemoveData() {
        System.out.println("\n*********** Starting testRemoveData ***********");
        // arrange
        Playlist current = mock(Playlist.class);

        // act
        accessSP.removeData(current);

        // assert
        verify(dataAccess).deletePairs(current);
        System.out.println("\n*********** Finished testRemoveData ***********");
    }
    @Test
    public void testInsertData() {
        System.out.println("\n*********** Starting testInsertData ***********");
        // arrange
        int pId = 1;
        String playlistName = "playlist1";
        int sId = 2;
        String songName = "song1";

        // act
        accessSP.insertData(pId, playlistName, sId, songName);

        // assert
        verify(dataAccess).insertPairs(pId, playlistName, sId, songName);
        System.out.println("\n*********** Finished testInsertData ***********");
    }
}
