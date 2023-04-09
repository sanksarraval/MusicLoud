package com.example.musicloud.business;

import com.example.musicloud.application.MyApp;
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
        playlistPersistence = MyApp.getPlaylistPersistence();
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

    /**
     * removePlaylist
     */
    public void removePlaylist(Playlist current){
        playlistPersistence.deletePlaylist(current);
    }


    public int getPlaylistId(String playlistName){
        playlists = playlistPersistence.getPlaylist();


        for(int i=0;i<playlists.size();i++){
            if(playlistName.equals(playlists.get(i).getPlaylistName())){
                return playlists.get(i).getId();
            }
        }
        return -1;
    }
}
