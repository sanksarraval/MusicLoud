package com.example.musicloud.objects;

import java.util.List;

public class Playlist {
    private final String playlistName;
    private final String description;
    private int playlistLength;
    private List<Song> allSongs;
    //album art

    public Playlist(String playlistName, String description, List songList) {
        this.playlistName = playlistName;
        this.description = description;
        this.allSongs = songList;
    }
}
