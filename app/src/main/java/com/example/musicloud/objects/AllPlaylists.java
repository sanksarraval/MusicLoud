package com.example.musicloud.objects;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicloud.application.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
