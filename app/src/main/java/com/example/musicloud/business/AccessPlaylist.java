package com.example.musicloud.business;

import com.example.musicloud.application.Services;
import com.example.musicloud.objects.Playlist;
import com.example.musicloud.persistence.PlaylistPersistence;

import java.util.Collections;
import java.util.List;

public class AccessPlaylist {
    private PlaylistPersistence playlistPersistence;
    private List<Playlist> playlists;

    //constructor creates instance of AccessPlaylist
    public AccessPlaylist(){
        playlistPersistence = Services.getPlaylistPersistence();
        playlists = null;
    }

    //getPlaylists method returns list of all playlists
    public List<Playlist> getPlaylists(){
        playlists = playlistPersistence.getPlaylist();
        return Collections.unmodifiableList(playlists);
    }


    public Playlist insertPlaylist (Playlist currentPlaylist){
        return playlistPersistence.insertPlaylist(currentPlaylist);
    }
}