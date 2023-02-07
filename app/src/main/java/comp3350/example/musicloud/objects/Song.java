package comp3350.example.musicloud.objects;

import java.io.*;

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

}
