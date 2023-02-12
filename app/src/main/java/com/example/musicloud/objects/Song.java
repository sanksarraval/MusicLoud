package com.example.musicloud.objects;

import androidx.annotation.NonNull;

public class Song {
    private final String songName;
    private final String artist;
    private final String albumName;
    private final String fileName;

    //private final File mp3;
    //private final File albumArt;

    public Song(String songName, String artist, String fileName) {
        this.songName = songName;
        this.artist = artist;
        this.fileName = fileName;
        albumName = "na"; //
    }

    public Song (String songName, String artist, String albumName, String fileName) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        this.fileName = fileName;
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
