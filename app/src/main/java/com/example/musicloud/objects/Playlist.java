package com.example.musicloud.objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private int id;
    private String playlistName;
    private String description;

    //playlist constructor with name, description and list of songs
    public Playlist(String playlistName, String description) {
        this.playlistName = playlistName;
        this.description = description;
    }

    //playlist constructor with name, and list of songs
    public Playlist(String playlistName) {
        this.playlistName = playlistName;
        this.description = null;
    }

    public Playlist(int id, String playlistName, String description) {
        this.id = id;
        this.playlistName = playlistName;
        this.description = description;
    }

    public Playlist(int playlistId, String pName) {
        this.id = playlistId;
        this.playlistName = pName;
        this.description = null;
    }

    //getter for name of playlist
    public String getPlaylistName(){
        return playlistName;
    }

    public void setPlaylistName(String newName){
        this.playlistName = newName;
    }
    //getter for playlist description
    public String getDescription(){
        return description;
    }

    public void setDescription (String newDescription){
        this.description = newDescription;
    }

    //tests equality of current playlist with second. Uses playlist length and name.
    public boolean equals(Object other) {
        boolean equals = false;
        boolean equals1 = false;

        if(other instanceof Playlist){
            final Playlist otherPlaylist = (Playlist) other;
            equals = Objects.equals(this.id, otherPlaylist.getId());
        }
        return equals;
    }
    public int getId(){ return id;}
    public void setId(int id){this.id = id;}

    @NonNull
    public String toString(){
        StringBuilder s = new StringBuilder("Playlist{");
        if (id>0){
            s.append("id ='").append(id).append("' ");
        }
        if (playlistName != null){
            s.append("playlistName='").append(playlistName).append("' ");
        }
        if (description != null){
            s.append("description='").append(description).append("' ");
        }
        s.append("}");

        return s.toString();
//        return String.format("\nPlaylist: \n id: %d \n name: %s \n description: %s", id, playlistName, description);
    }
}
