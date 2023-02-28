package com.example.musicloud.business;

import com.example.musicloud.application.Services;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SongPersistence;

import java.util.Collections;
import java.util.List;

public class AccessSongs {
    private final SongPersistence songPersistence;
    private List<Song> songs;
    private List<String> songNames;
    private int currentSong;

    public AccessSongs(){
        songPersistence = Services.getSongPersistence();
        songs = null;
        songNames = null;
        currentSong = 0;
    }

    public List<Song> getSongs(){
        songs = songPersistence.getAllSongs();
        return Collections.unmodifiableList(songs);
    }

    public Song insertSong(Song currentSong){
        return songPersistence.insertSong(currentSong);
    }

    public Song updateSong (Song currentSong){
        return songPersistence.updateSong(currentSong);
    }

    public void deleteSong(Song currentSong){
        songPersistence.deleteSong(currentSong);
    }

    public List<String> getSongNames(){
        songNames = songPersistence.allSongNames();
        return songNames;
    }
    
}
