package com.example.musicloud.stubs;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.SP;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SPPersistence;

import java.util.ArrayList;
import java.util.List;

public class SPPersistenceStub implements SPPersistence {
    private List<SP> sps;

    /**
     * constructor
     */
    public SPPersistenceStub() {
        this.sps = new ArrayList<>();
        final Playlist playlist1 = new Playlist("playlist1", "description");
        final Playlist playlist2 = new Playlist("playlist2", "description2");

        final Song song = new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give");
        final Song song1 = new Song("Rain Man", "Ketsa", "Ketsa - Rain Man");
        final Song song2 = new Song("Above the Clouds", "Scott Holmes Music", "Scott Holmes Music - Above the Clouds.mp3");

        this.sps.add(new SP(song, playlist1));
        this.sps.add(new SP(song1, playlist2));
        this.sps.add(new SP(song2, playlist1));
    }

    /**
     * returns list of all SP objects with song name passed
     */
    @Override
    public List<SP> getSP(String songName) {
        List<SP> newSPs;
        SP sp;
        int counter;

        newSPs = new ArrayList<>();
        for (counter = 0; counter<sps.size(); counter++){
            sp = sps.get(counter);

            if(sp.getSongName().equals(songName)){
                newSPs.add(sps.get(counter));
            }
        }
        return newSPs;
    }

    /**
     * returns list of all SP objects with playlist name passed
     */
    @Override
    public List<SP> getPS (String playlistName) {
        List<SP> newSPs;
        SP ps;
        int counter;

        // get the SP objects with the same playlistName as currentSP
        newSPs = new ArrayList<>();
        for (counter=0; counter<sps.size(); counter++)
        {
            ps = sps.get(counter);
            if (ps.getPlaylistName().equals(playlistName))
            {
                newSPs.add(sps.get(counter));
            }
        }
        return newSPs;
    }

    public void deletePairs(Playlist current){

    }


    public void insertPairs(int pId, String playlistName, int sId, String songName){

    }
}
