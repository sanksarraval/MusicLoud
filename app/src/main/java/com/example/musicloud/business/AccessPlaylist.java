package com.example.musicloud.business;

import com.example.musicloud.application.MyApp;
import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.User;
import com.example.musicloud.persistence.PlaylistPersistence;
import com.example.musicloud.persistence.UserManagement;

import java.util.Collections;
import java.util.List;

public class AccessPlaylist {
    /**
     * PlaylistPersistence instance
     * */
    PlaylistPersistence playlistPersistence;
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
    public AccessPlaylist(PlaylistPersistence AP) {
        playlistPersistence = AP;
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
            if(playlistName.toLowerCase().equals(playlists.get(i).getPlaylistName().toLowerCase())){
                return playlists.get(i).getId();
            }
        }
        return -1;
    }

    /**
     * method to validate if the new playlist to be added has valid
     * input from user
     * @param size size of the new playlist
     * @param playlistName new playlist name
     * @return true is playlist not exists already and size is more than 0
     */
    public boolean canAddPlaylist(int size, String playlistName){
        playlists = playlistPersistence.getPlaylist();
        int index = -1;
        for(int i=0;i<playlists.size();i++){
            if(playlistName.toLowerCase().equals(playlists.get(i).getPlaylistName().toLowerCase())){
                index =  playlists.get(i).getId();
                break;
            }
        }

        return (!playlistName.trim().equals("") && index == -1 && size>0);
    }
}
