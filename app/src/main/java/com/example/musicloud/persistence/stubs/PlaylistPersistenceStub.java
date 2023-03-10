package com.example.musicloud.persistence.stubs;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.PlaylistPersistence;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistPersistenceStub implements PlaylistPersistence {
    private List<Playlist> playlists;

    /**
     * constructor
     */
    public PlaylistPersistenceStub(){
        this.playlists = new ArrayList<>();

        playlists.add(new Playlist("name", "description"));
    }

    /**
     * @return list of all playlists
     */
    @Override
    public List<Playlist> getPlaylist() {
        return Collections.unmodifiableList(playlists);
    }

    /**
     * inserts playlist into "database"
     */
    @Override
    public Playlist insertPlaylist(Playlist currentPlaylist) {
        playlists.add(currentPlaylist);
        return currentPlaylist;
    }

    /**
     * updates playlist with one passed to method
     */
    @Override
    public Playlist updatePlaylist(Playlist currentPlaylist) {
        int index;

        index = playlists.indexOf(currentPlaylist);
        if (index >= 0) {
            playlists.set(index, currentPlaylist);
        }
        return currentPlaylist;
    }

    /**
     * deletes playlist
     */
    @Override
    public void deletePlaylist(Playlist currentPlaylist) {
        int index;

        index = playlists.indexOf(currentPlaylist);
            if (index >= 0) {
                playlists.remove(index);
            }
    }
}
