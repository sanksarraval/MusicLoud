package com.example.musicloud.persistence.HSQLDB;

import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.hsqldb.SongPersistenceHSQLDB;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SongPersistenceHSQLDBIntegrationTest {

    private SongPersistenceHSQLDB mActualSongPersistenceHSQLDB;

    @Before
    public void setUp() {
        /*
         * The virtual database
         */
        mActualSongPersistenceHSQLDB = new SongPersistenceHSQLDB("src/test/HSQLDB/app_db/SC");
    }

    @Test
    public void testSongHsqldb() {
        //Look up all the songs
        List<Song> actualSongList = mActualSongPersistenceHSQLDB.getAllSongs();
        int len = actualSongList.size();
        Song secondSong = null;
        for (int i = 0; i < len; i++) {
            Song song = actualSongList.get(i);
            if (i == 1) {
                secondSong = song;
            }
            System.out.println("song==" + song.toString());
        }

        //Test modify second
        if (secondSong != null) {
            Song songUpdate = new Song(secondSong.getId(), secondSong.getSongName() + "-update", secondSong.getArtist() + "-update", secondSong.getAlbumName() + "-update", secondSong.isLiked());
            mActualSongPersistenceHSQLDB.updateSong(songUpdate);

            System.out.println("Updated data==" + mActualSongPersistenceHSQLDB.getSong(secondSong.getId()));
        }

        //Test a new piece of music data
        Song songInsert = new Song("test-name-insert", "test-name-insert", "test-name-insert", true);
        mActualSongPersistenceHSQLDB.insertSong(songInsert);
        System.out.println("The first data added is complete==" + songInsert);
        Song songInsert02 = new Song("test-name-insert02", "test-name-insert02", "test-name-insert02", false);
        mActualSongPersistenceHSQLDB.insertSong(songInsert02);
        System.out.println("The second data is added==" + songInsert02);

        List<String> songNameList = mActualSongPersistenceHSQLDB.allSongNames();
        System.out.println("All the song titles==" + songNameList.toString());

        //Delete the newly added data
        mActualSongPersistenceHSQLDB.deleteSong(songInsert);

        //Get all the data again
        List<String> songNameListNew = mActualSongPersistenceHSQLDB.allSongNames();
        System.out.println("Get all the song names again==" + songNameListNew.toString());

    }


}
