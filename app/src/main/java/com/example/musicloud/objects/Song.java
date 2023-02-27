package com.example.musicloud.objects;

import androidx.annotation.NonNull;

import java.io.*;

public class Song {
    private final String songName;
    private final String artist;
    private final String albumName;
    public String getSongName;

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


    //method to compare with other song's name
    public int compareTo(Song other){
        return songName.compareTo(other.songName);
    }


    //toString to get song summary
    @NonNull
    public String toString(){
        return "Song Name:"+songName + " ArtistName:" +artist + " AlbumName:"+albumName;
    }

    //getter for song name
    public String getSongName(){
        return songName;
    }

}
