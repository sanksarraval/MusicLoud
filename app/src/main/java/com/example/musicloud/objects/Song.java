package com.example.musicloud.objects;

import androidx.annotation.NonNull;

public class Song {
    private final String songName;
    private final String artist;
    private final String albumName;

    //private final File mp3;
    //private final File albumArt;

    public Song(String songName, String artist) {
        this.songName = songName;
        this.artist = artist;
        albumName = "na"; //
    }

    public Song (String songName, String artist, String albumName) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
    }

    //added isEqual to compare with some other song's data
    public boolean isEqual(String songName, String artistName, String albumName){
        return (this.songName.equals(songName)
                &&  this.artist.equals(artistName) && this.albumName.equals(albumName));
    }

    //method to compare with other song's name
    public int compareTo(Song other){
        return songName.compareTo(other.songName);
    }


    //getter for song name
    public String getSongName(){
        return songName;
    }

}
