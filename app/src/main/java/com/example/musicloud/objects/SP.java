package com.example.musicloud.objects;

import androidx.annotation.NonNull;

public class SP {
    private final Song song;
    private final Playlist playlist;


    public SP(Song song, Playlist playlist) {
        this.song = song;
        this.playlist = playlist;
    }

    public String getSongName(){ return song.getSongName();}

    public String getPlaylistName(){ return playlist.getPlaylistName();}

    @NonNull
    public String toString(){
        return String.format("SP: %s %s", song.getSongName(), playlist.getPlaylistName());
    }
}
