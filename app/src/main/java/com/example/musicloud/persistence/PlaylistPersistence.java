package com.example.musicloud.persistence;

import com.example.musicloud.objects.Playlist;

import java.util.List;

public interface PlaylistPersistence {
    /**
     * list of all playlists
     */
    List<Playlist> getPlaylist();

    /**
     * insert playlist into database
     */
    Playlist insertPlaylist (final Playlist currentPlaylist);

    /**
     * update playlist
     */
    Playlist updatePlaylist (Playlist currentPlaylist);

    /**
     * delete playlist
     */
    void deletePlaylist (Playlist currentPlaylist);
}
