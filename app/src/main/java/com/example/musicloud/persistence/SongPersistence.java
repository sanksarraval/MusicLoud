package com.example.musicloud.persistence;

import java.util.List;

import com.example.musicloud.objects.Song;

public interface SongPersistence {

    /**
     * list of all songs
     */
    List<Song> getAllSongs();
    //alpha order by artist then song

    /**
     * get song based on index
     */
    Song getSong (int i);

    /**
     * get size of database
     */
    int getSize ();

    /**
     * insert song
     */
    Song insertSong (Song currentSong);

    /**
     * update song
     */
    Song updateSong (Song currentSong);

    /**
     * delete song
     */
    void deleteSong (Song currentSong);

    /**
     * returns list of all song names
     */
    List<String> allSongNames();

    /**
     * likes song
     */
    void likeSong (Song currentSong);

    /**
     * unlikes song
     */
    void unlikeSong (Song currentSong);

    /**
     * returns whether song has been liked
     */
    boolean isLiked(Song currentSong);

    /**
     * returns list of all songs that have been liked
     */
    List<Song> getLikedSongs();

}
