package com.example.musicloud.business;

import com.example.musicloud.application.Services;
import com.example.musicloud.objects.AllPlaylists;
import com.example.musicloud.objects.Playlist;
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
     * List of all songs
     */
    private List<Song> songs;
    /**
     * List of all song names
     */
    private List<String> songNames;

    private AllPlaylists allP;

    /**
     * constructor
     */
    public AccessSongs(){
        songPersistence = Services.getSongPersistence();
        songs = null;
        songNames = null;


        //FAKE DB
        Playlist p1 = new Playlist("P1");
        p1.addSong(new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give"));
        p1.addSong(new Song("Nightfall", "Stereohada", "Stereohada - Nightfall"));

        Playlist p2 = new Playlist("P2");
        p2.addSong(new Song("Rain Man","Ketsa","Ketsa - Rain Man"));
        p2.addSong(new Song("Nightfall", "Stereohada", "Stereohada - Nightfall"));

        AllPlaylists allP = new AllPlaylists();
        allP.add(p1);
        allP.add(p2);

    }

    /**
     * getSongs
     * @return list of all songs in songPersistence
     */
    public List<Song> getSongs(){
        songs = songPersistence.getAllSongs();
        return Collections.unmodifiableList(songs);
    }

    /**
     * @param currentSong song to insert
     * @return the song passed after inserting
     */
    public Song insertSong(Song currentSong){
        return songPersistence.insertSong(currentSong);
    }

    /**
     * updateSong
     * @param currentSong the preexisting song with updated information
     * @return the song passed after updating
     */
    public Song updateSong (Song currentSong){
        return songPersistence.updateSong(currentSong);
    }

    /**
     * deleteSong
     * @param currentSong song to be deleted
     */
    public void deleteSong(Song currentSong){
        songPersistence.deleteSong(currentSong);
    }

    /**
     * likeSong
     * @param currentSong song to be liked
     */
    public void likeSong(Song currentSong) { songPersistence.likeSong(currentSong); }

    /**
     * unlikeSong
     * @param currentSong song to unlike
     */
    public void unlikeSong(Song currentSong) { songPersistence.unlikeSong(currentSong); }

    /**
     * isLiked
     * @param currentSong is this song liked?
     * @return whether the song passed has been liked
     */
    public boolean isLiked(Song currentSong) { return songPersistence.isLiked(currentSong); }


    /**
     * getLikedSongs
     * @return list of all songs that have been liked
     */
    public List<Song> getLikedSongs() { return songPersistence.getLikedSongs(); }

    /**
     * getSongNames
     * @return list of all song names
     */
    public List<String> getSongNames(){
        songNames = songPersistence.allSongNames();
        return songNames;
    }

    //USES FAKE DB, to return an AllPlaylists object, fake db for testing
    public AllPlaylists getAllPlaylists(){

        return allP;
    }


}
