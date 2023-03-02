package com.example.musicloud.business;

import com.example.musicloud.application.Services;
import com.example.musicloud.objects.Playlist;
import com.example.musicloud.persistence.PlaylistPersistence;

import java.util.Collections;
import java.util.List;

public class AccessPlaylist {
    private PlaylistPersistence playlistPersistence;
    private List<Playlist> playlists;
    private Playlist playlist;
    private int currentPlaylist;

    public AccessPlaylist(){
        playlistPersistence = Services.getPlaylistPersistence();
        playlists = null;
        playlist = null;
        currentPlaylist = 0;
    }

    public List<Playlist> getPlaylists(){
        playlists = playlistPersistence.getPlaylist();
        return Collections.unmodifiableList(playlists);
    }

    public Playlist insertPlaylist (Playlist currentPlaylist){
        return playlistPersistence.insertPlaylist(currentPlaylist);
    }
    public Playlist updatePlaylist (Playlist currentPlaylist){
        return playlistPersistence.updatePlaylist(currentPlaylist);
    }
    public void deletePlaylist(Playlist currentPlaylist){
        playlistPersistence.deletePlaylist(currentPlaylist);
    }

}
