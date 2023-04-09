package com.example.musicloud.objects;

import java.util.ArrayList;
import java.util.List;

public class AllPlaylists {
    private List<Playlist> allP;


    public AllPlaylists(){
        allP = new ArrayList<Playlist>();
    }

    //add method
    public void add(Playlist obj){
        allP.add(obj);
    }

    //remove method
    public void remove(Playlist rem){
        allP.remove(rem);
    }

    //getSize
    public int size(){
        return allP.size();
    }

    //get method
    public Playlist get(int i){
        return allP.get(i);
    }

}
