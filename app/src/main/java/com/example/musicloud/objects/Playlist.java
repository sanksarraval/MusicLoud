package com.example.musicloud.objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private final String playlistName;
    private final String description;
    private double playlistLength; //playlist length in hours
    //album art

    //playlist constructor with name, description and list of songs
    public Playlist(String playlistName, String description) {
        this.playlistName = playlistName;
        this.description = description;
    }

    //playlist constructor with name, and list of songs
    public Playlist(String playlistName) {
        this.playlistName = playlistName;
        this.description = null;
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

    @NonNull
    public String toString(){
        return String.format("\nPlaylist: \n name: %s \n description: %s", playlistName, description);
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
