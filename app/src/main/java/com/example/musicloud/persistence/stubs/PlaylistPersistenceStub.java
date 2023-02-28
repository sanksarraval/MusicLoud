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
    public PlaylistPersistenceStub(){
        this.playlists = new ArrayList<>();

        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give"));
        songs.add(new Song("Rain Man", "Ketsa", "Ketsa - Rain Man"));

        playlists.add(new Playlist("name", "description", songs));

    }
    @Override
    public List<Playlist> getPlaylist() {
        return Collections.unmodifiableList(playlists);
    }

    @Override
    public Playlist insertPlaylist(Playlist currentPlaylist) {
        playlists.add(currentPlaylist);
        return currentPlaylist;
    }

    @Override
    public Playlist updatePlaylist(Playlist currentPlaylist) {
        int index;

        index = playlists.indexOf(currentPlaylist);
        if (index >= 0){
            playlists.set(index,currentPlaylist);
        }
        return currentPlaylist;
    }

    @Override
    public void deletePlaylist(Playlist currentPlaylist) {
        int index;

        index = playlists.indexOf(currentPlaylist);
        if(index >= 0){
            playlists.remove(index);
        }
    }
}
