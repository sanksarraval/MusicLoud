package com.example.musicloud.objects;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private final String playlistName;
    private final String description;
    private double playlistLength; //playlist length in hours
    private List<Song> allSongs;
    //album art

    //playlist constructor with name, description and list of songs
    public Playlist(String playlistName, String description, List<Song> songList) {
        this.playlistName = playlistName;
        this.description = description;
        this.playlistLength = -1;
        this.allSongs = songList;
    }

    //playlist constructor with name, and list of songs
    public Playlist(String playlistName, List<Song> songList) {
        this.playlistName = playlistName;
        this.description = null;
        this.playlistLength = -1;
        this.allSongs = songList;
    }

    //getter for name of playlist
    public String getPlaylistName(){
        return playlistName;
    }

    //getter for playlist description
    public String getDescription(){
        return description;
    }

    //returns length of entire playlist in hours
    public double getPlaylistLength(){
        return playlistLength;
    }

    //getter for list of songs in playlist
    public List<Song> getSongList (){
        return allSongs;
    }

    @NonNull
    public String toString(){
        return String.format("Playlist: %s %s %f", playlistName, description, playlistLength);
    }

    //tests equality of current playlist with second. Uses playlist length and name.
    public boolean equals(Object other) {
        boolean equals = false;
        boolean equals1 = false;

        if(other instanceof Playlist){
            final Playlist otherPlaylist = (Playlist) other;
            equals = Objects.equals(this.playlistLength, otherPlaylist.getPlaylistLength());
            equals1 = Objects.equals(this.playlistName, otherPlaylist.getPlaylistName());
        }
        return equals&&equals1;
    }
}
