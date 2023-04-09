package com.example.musicloud.objects;

import androidx.annotation.NonNull;

public class SP {
    private final Song song;
    private final Playlist playlist;


    /**
     * constructor
     */
    public SP(Song song, Playlist playlist) {
        this.song = song;
        this.playlist = playlist;
    }

    /**
     * @return songID
     */
    public int getSongID (){return song.getId();}

    /**
     * @return song name
     */
    public String getSongName(){ return song.getSongName();}

    /**
     * @return playlist name
     */
    public String getPlaylistName(){ return playlist.getPlaylistName();}

    /**
     * @return playlist id
     */
    public int getPlaylistId(){ return playlist.getId();}

    /**
     * to string for SP object
     */
    @NonNull
    public String toString(){
        return String.format("SP: \nSong: %s\nPlaylist: %s", song.getSongName(), playlist.getPlaylistName());
    }

    public Song getSong(){
        return song;
    }
}
