package com.example.musicloud.business;

import com.example.musicloud.application.Services;
import com.example.musicloud.objects.SP;
import com.example.musicloud.persistence.SPPersistence;

import java.util.List;

public class AccessSP {
    private SPPersistence dataAccess;
    private List<SP> elements;
    private SP songPlaylist;
    private int currentSP;
    private int currentPS;

    public AccessSP() {
        dataAccess = Services.getSpPersistence();
        elements = null;
        currentSP = 0;
        currentPS = 0;
    }

    public AccessSP (final SPPersistence spPersistence) {
        this();
        this.dataAccess = spPersistence;
    }

    public SP getSP (String songName){
        if (elements == null) {
            elements = dataAccess.getSP(songName);
            currentSP = 0;
        }
        if (currentSP < elements.size()){
            songPlaylist = elements.get(currentSP);
            currentSP++;
        } else {
            elements = null;
            songPlaylist = null;
            currentSP = 0;
        }
        return songPlaylist;
    }

    public SP getPS(String playlistName){
        if (elements == null){
            elements = dataAccess.getPS(playlistName);
            currentPS = 0;
        }
        if (currentPS < elements.size()){
            songPlaylist = elements.get(currentPS);
            currentPS++;
        } else {
            elements = null;
            songPlaylist = null;
            currentPS = 0;
        }
        return songPlaylist;
    }
}
