package com.example.musicloud.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SongPersistence;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

public class AccessSongsTest {
    @Mock
    private SongPersistence songPersistence;

    private AccessSongs accessSongs;

    @Before
    public void setUp() {
        songPersistence = mock(SongPersistence.class);
        accessSongs = new AccessSongs(songPersistence);
    }

    @Test
    public void testGetSongs() {
        System.out.println("\n*********** Starting testGetSongs ***********");
        // Arrange
        List<Song> expectedSongs = Arrays.asList(new Song("Song1", "Artist1"), new Song("Song2", "Artist2"));
        when(songPersistence.getAllSongs()).thenReturn(expectedSongs);

        // Act
        List<Song> actualSongs = accessSongs.getSongs();

        // Assert
        assertEquals(expectedSongs, actualSongs);

        System.out.println("\n*********** Finished testGetSongs ***********");
    }

    @Test
    public void testInsertSong() {

        System.out.println("\n*********** Starting testInsertSong ***********");
//         Arrange
        Song songToInsert = new Song("good song", "artist");
        Song expectedSong = new Song("good song", "artist");
        when(songPersistence.insertSong(songToInsert)).thenReturn(expectedSong);

        // Act
        Song actualSong = accessSongs.insertSong(songToInsert);

        // Assert
        assertEquals(expectedSong, actualSong);
        System.out.println("\n*********** Finished testInsertSong ***********");
    }
    @Test
    public void testUpdateSong() {

        System.out.println("\n*********** Starting testUpdateSong ***********");
//         Arrange
        Song songToUpdate = new Song("good song", "artist");
        Song expectedSong = new Song("good song", "artist updated");
        when(songPersistence.updateSong(songToUpdate)).thenReturn(expectedSong);

        // Act
        Song actualSong = accessSongs.updateSong(songToUpdate);

        // Assert
        assertEquals(expectedSong, actualSong);
        System.out.println("\n*********** Finished testUpdateSong ***********");
    }
    @Test
    public void testDeleteSong() {
        System.out.println("\n*********** Starting testDeleteSong ***********");
        // Arrange
        Song songToRemove = new Song("Song 1", "artist");

        // Act
        accessSongs.deleteSong(songToRemove);

        // Assert
        verify(songPersistence).deleteSong(songToRemove);
        System.out.println("\n*********** Finished testDeleteSong ***********");
    }
    @Test
    public void testLikeSong() {
        System.out.println("\n*********** Starting testLikeSong ***********");
        // Arrange
        Song songToLike = new Song("Song 1", "artist");

        // Act
        accessSongs.likeSong(songToLike);

        // Assert
        verify(songPersistence).likeSong(songToLike);
        System.out.println("\n*********** Finished testLikeSong ***********");
    }

    @Test
    public void testUnlikeSong() {
        System.out.println("\n*********** Starting testUnlikeSong ***********");
        // Arrange
        Song songToUnlike = new Song("Song", "artist", "album", true);

        // Act
        accessSongs.unlikeSong(songToUnlike);

        // Assert
        verify(songPersistence).unlikeSong(songToUnlike);
        System.out.println("\n*********** Finished testUnlikeSong ***********");
    }

    @Test
    public void testisLiked() {
        System.out.println("\n*********** Starting testisLiked ***********");
        // Arrange
        Song testSong = new Song("Song", "artist", "album", true);
        when(songPersistence.isLiked(testSong)).thenReturn(true);
        // Act
        boolean result = accessSongs.isLiked(testSong);

        // Assert
        verify(songPersistence).isLiked(testSong);
        assertTrue(result);
        System.out.println("\n*********** Finished testisLiked ***********");
    }
    @Test
    public void testGetLikedSongs() {
        System.out.println("\n*********** Starting testGetLikedSongs ***********");
        // Arrange
        List<Song> songs = Arrays.asList(new Song("Song1", "Artist1", "Album1", true),
                new Song("Song2", "Artist2", "Album2", false),
                new Song("Song3", "Artist3", "Album3", true));
        List<Song> expectedSongs = Arrays.asList(new Song("Song1", "Artist1", "Album1", true),
                new Song("Song3", "Artist3", "Album3", true));
        when(songPersistence.getLikedSongs()).thenReturn(expectedSongs);

        // Act
        List<Song> actualSongs = accessSongs.getLikedSongs();

        // Assert
        assertEquals(expectedSongs, actualSongs);

        System.out.println("\n*********** Finished testGetLikedSongs ***********");
    }
    @Test
    public void getSongNames() {
        System.out.println("\n*********** Starting getSongNames ***********");
        // Arrange
        List<Song> songs = Arrays.asList(new Song("Song1", "Artist1", "Album1", true),
                new Song("Song2", "Artist2", "Album2", false),
                new Song("Song3", "Artist3", "Album3", true));
        List<String> expectedNames = Arrays.asList("Song1", "Song2", "Song3");
        when(songPersistence.allSongNames()).thenReturn(expectedNames);

        // Act
        List<String> actualNames = accessSongs.getSongNames();

        // Assert
        assertEquals(expectedNames, actualNames);

        System.out.println("\n*********** Finished getSongNames ***********");
    }

}
