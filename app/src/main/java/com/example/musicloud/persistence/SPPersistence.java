package com.example.musicloud.persistence;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.SP;

import java.util.List;

public interface SPPersistence {
    /**
     * returns list of all SP objects with song name passed to method
     */
    List<SP> getSP (final String songName);

    /**
     * returns list of all PS objects with playlist name passed to method
     */
    List<SP> getPS (final String playlistName);

    void deletePairs(Playlist current);

     void insertPairs(int pId, String playlistName, int sId, String songName);

}
