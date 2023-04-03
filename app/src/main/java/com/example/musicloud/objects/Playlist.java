package com.example.musicloud.objects;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicloud.application.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Playlist {

    //name and songs list
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        songs = new ArrayList<Song>();
    }

    //add song method
    public void addSong(Song songN){
        if(songN != null){
            songs.add(songN);
        }
    }

    //remove song method
    public void removeSong(Song songR){
        if(songR != null){
            songs.remove(songR);
        }
    }

    //to get all song list
    public List<Song> getSongs(){
        return songs;
    }

    //to get playlist name
    public String getName(){
        return name;
    }

    public List<String> getSongNames(){
        List<String> names = new ArrayList<String>();

        for(int i=0;i<songs.size();i++){
            names.add(songs.get(i).getSongName());
        }
        return names;
    }
}