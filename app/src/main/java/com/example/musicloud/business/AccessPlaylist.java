package com.example.musicloud.business;

import com.example.musicloud.application.Services;
import com.example.musicloud.objects.Playlist;
import com.example.musicloud.persistence.PlaylistPersistence;

import java.util.Collections;
import java.util.List;

public class AccessPlaylist {
    /**
     * PlaylistPersistence instance
     * */
    private PlaylistPersistence playlistPersistence;
    /**
     * list of all playlists
     */
    private List<Playlist> playlists;

    /**
     * constructor
     */
    public AccessPlaylist(){
        playlistPersistence = Services.getPlaylistPersistence();
        playlists = null;
    }

    /**
     * getPlaylists
     *
     * @return list of all playlists
     */
    public List<Playlist> getPlaylists(){
        playlists = playlistPersistence.getPlaylist();
        return Collections.unmodifiableList(playlists);
    }

    /**
     * insertPlaylist
     * @param currentPlaylist playlist to be inserted
     * @return the new playlist that was inserted
     */
    public Playlist insertPlaylist (Playlist currentPlaylist){
        return playlistPersistence.insertPlaylist(currentPlaylist);
    }
}
