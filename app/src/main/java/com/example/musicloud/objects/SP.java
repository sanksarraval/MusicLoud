package com.example.musicloud.objects;

import androidx.annotation.NonNull;

public class SP {
    private final Song song;
    private final Playlist playlist;


    public SP(Song song, Playlist playlist) {
        this.song = song;
        this.playlist = playlist;
    }

    public int getSongID (){return song.getId();}
    public String getSongName(){ return song.getSongName();}

    public String getPlaylistName(){ return playlist.getPlaylistName();}
    public int getPlaylistId(){ return playlist.getId();}
    @NonNull
    public String toString(){
        return String.format("SP: \nSong: %s\nPlaylist: %s", song.getSongName(), playlist.getPlaylistName());
    }
}
