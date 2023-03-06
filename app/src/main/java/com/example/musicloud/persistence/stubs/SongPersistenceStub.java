package com.example.musicloud.persistence.stubs;

import androidx.annotation.NonNull;

import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SongPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongPersistenceStub implements SongPersistence {
    //list of all songs in users library
    private List<Song> songs;

    public SongPersistenceStub(){
        this.songs = new ArrayList<>();

        songs.add(new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give"));
        songs.add(new Song("Rain Man", "Ketsa", "Ketsa - Rain Man"));
        songs.add(new Song("Above the Clouds", "Scott Holmes Music", "Scott Holmes Music - Above the Clouds.mp3"));
        songs.add(new Song("Stereohada", "Nightfall", "Stereohada - Nightfall.mp3"));
    }

    @Override
    public List<Song> getAllSongs() {
        return songs;
    }

    @Override
    public Song getSong(int i) {
        return songs.get(i);
    }

    @Override
    public int getSize() {
        return songs.size();
    }

    @Override
    public Song insertSong(Song currentSong) {
        songs.add(currentSong);
        return currentSong;
    }

    @Override
    public Song updateSong(Song currentSong) {
        int index;

        index = songs.indexOf(currentSong);
        if (index >= 0)
        {
            songs.set(index, currentSong);
        }
        return currentSong;
    }

    @Override
    public void deleteSong(Song currentSong) {
        int index;

        index = songs.indexOf(currentSong);
        if (index >= 0)
        {
            songs.remove(index);
        }
    }

    @Override
    public List<String> allSongNames() {
        ArrayList<String> songNames = new ArrayList<>();

        for(int i=0;i<songs.size();i++){
            songNames.add(songs.get(i).getSongName());
        }

        return songNames;
    }

    @NonNull
    public String toString() {
        StringBuilder ans = new StringBuilder();

        for(int i=0;i<songs.size();i++){
            ans.append(songs.get(i)).append("\n");
        }

        ans.append("This is the summary of the songs object\nNumber of Songs:").append(songs.size());
        return ans.toString();
    }
}
