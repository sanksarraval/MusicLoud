package com.example.musicloud.objects;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private final String playlistName;
    private final String description;
    private double playlistLength;
    private List<Song> allSongs;
    //album art

    public Playlist(String playlistName, String description, List<Song> songList) {
        this.playlistName = playlistName;
        this.description = description;
        this.playlistLength = -1;
        this.allSongs = songList;
    }
    public Playlist(String playlistName, List<Song> songList) {
        this.playlistName = playlistName;
        this.description = null;
        this.playlistLength = -1;
        this.allSongs = songList;
    }

    public String getPlaylistName(){
        return playlistName;
    }

    public String getDescription(){
        return description;
    }

    //returns length of entire playlist in minutes
    public double getPlaylistLength(){
        return playlistLength;
    }

    public List<Song> getSongList (){
        return allSongs;
    }

    @NonNull
    public String toString(){
        return String.format("Playlist: %s %s %f", playlistName, description, playlistLength);
    }

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
