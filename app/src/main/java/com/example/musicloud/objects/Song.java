package com.example.musicloud.objects;

import android.util.Log;

import androidx.annotation.NonNull;

public class Song {
    private int id;
    private final String songName;
    private final String artist;
    private final String albumName;
    private boolean liked;

    private static Song currentSong;

    //private final File mp3;
    //private final File albumArt;

    public Song(String songName, String artist) {
        this.songName = songName;
        this.artist = artist;
        albumName = "na";
        liked = false;
        currentSong = null;
    }

    public Song (String songName, String artist, String albumName) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        currentSong = null;
    }

    public Song (String songName, String artist, String albumName, boolean liked) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        this.liked = liked;
    }

    public Song(int id, String songName, String artist, String albumName, boolean liked) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        this.liked = liked;
        currentSong = null;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean isLiked() { return liked; }

    public void setLiked() {
        liked = !liked;
    }

    public void setCurrentSong(){
        currentSong = this;
    }
    @NonNull
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                ", albumName='" + albumName + '\'' +
                ", liked=" + liked +
                '}';
    }
}
