package com.example.musicloud.business;

import com.example.musicloud.application.Services;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SongPersistence;

import java.util.Collections;
import java.util.List;

public class AccessSongs {
    /**
     * SongPersistence instance
     */
    private final SongPersistence songPersistence;


    /**
     * constructor
     */
    public AccessSongs() {
        songPersistence = Services.getSongPersistence();
    }

    /**
     * getSongs
     *
     * @return list of all songs in songPersistence
     */
    public List<Song> getSongs() {
        return Collections.unmodifiableList(songPersistence.getAllSongs());
    }

    /**
     * @param currentSong song to insert
     * @return the song passed after inserting
     */
    public Song insertSong(Song currentSong) {
        return songPersistence.insertSong(currentSong);
    }

    /**
     * updateSong
     *
     * @param currentSong the preexisting song with updated information
     * @return the song passed after updating
     */
    public Song updateSong(Song currentSong) {
        return songPersistence.updateSong(currentSong);
    }

    /**
     * deleteSong
     *
     * @param currentSong song to be deleted
     */
    public void deleteSong(Song currentSong) {
        songPersistence.deleteSong(currentSong);
    }

    /**
     * likeSong
     *
     * @param currentSong song to be liked
     */
    public void likeSong(Song currentSong) {
        songPersistence.likeSong(currentSong);
    }

    /**
     * unlikeSong
     *
     * @param currentSong song to unlike
     */
    public void unlikeSong(Song currentSong) {
        songPersistence.unlikeSong(currentSong);
    }

    /**
     * isLiked
     *
     * @param currentSong is this song liked?
     * @return whether the song passed has been liked
     */
    public boolean isLiked(Song currentSong) {
        return songPersistence.isLiked(currentSong);
    }


    /**
     * getLikedSongs
     *
     * @return list of all songs that have been liked
     */
    public List<Song> getLikedSongs() {
        return songPersistence.getLikedSongs();
    }

    /**
     * getSongNames
     *
     * @return list of all song names
     */
    public List<String> getSongNames() {
        return songPersistence.allSongNames();
    }

}
