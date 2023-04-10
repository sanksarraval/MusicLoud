package com.example.musicloud.stubs;

import androidx.annotation.NonNull;

import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SongPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongPersistenceStub implements SongPersistence {
    private final List<Song> songs;
    private final List<Song> likedSongs;

    /**
     * constructor
     */
    public SongPersistenceStub(List<Song> likedSongs) {
        this.likedSongs = likedSongs;
        this.songs = new ArrayList<>();

        songs.add(new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give"));
        songs.add(new Song("Rain Man", "Ketsa", "Ketsa - Rain Man"));
        songs.add(new Song("Nightfall", "Stereohada", "Nightfall.mp3"));
    }

    /**
     * returns list all songs
     */
    @Override
    public List<Song> getAllSongs() {

        return Collections.unmodifiableList(songs);
    }

    /**
     * @return song based on index
     */
    @Override
    public Song getSong(int i) {
        return songs.get(i);
    }

    /**
     * @return size of song "database"
     */
    @Override
    public int getSize() {
        return songs.size();
    }

    /**
     * inserts song into "database"
     */
    @Override
    public Song insertSong(Song currentSong) {
        songs.add(currentSong);
        return currentSong;
    }

    /**
     * updates song with one passed to method
     */
    @Override
    public Song updateSong(Song currentSong) {
        int index;

        index = songs.indexOf(currentSong);
        if (index >= 0) {
            songs.set(index, currentSong);
        }
        return currentSong;
    }

    /**
     * delete song
     */
    @Override
    public void deleteSong(Song currentSong) {
        int index;

        index = songs.indexOf(currentSong);
        if (index >= 0) {
            songs.remove(index);
        }
    }

    /**
     * returns list of all string names
     */
    @Override
    public List<String> allSongNames() {
        ArrayList<String> songNames = new ArrayList<>();

        for (int i = 0; i < songs.size(); i++) {
            songNames.add(songs.get(i).getSongName());
        }
        return songNames;
    }

    private static final String TAG = "SongPersistenceStub";

    /**
     *to string for song "database"
     */
    @NonNull
    public String toString() {
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < songs.size(); i++) {
            ans.append(songs.get(i)).append("\n");
        }

        ans.append("This is the summary of the songs object\nNumber of Songs:").append(songs.size());
        return ans.toString();
    }

    /**
     * likes song in database
     */
    @Override
    public void likeSong(Song currentSong) {
        currentSong.setLiked();
        likedSongs.add(currentSong);
    }

    /**
     * unlikes song in database
     */
    @Override
    public void unlikeSong(Song currentSong) {
        currentSong.setLiked();
        int index = likedSongs.indexOf(currentSong);
        if (index >= 0) {
            likedSongs.remove(index);
        }
    }

    /**
     * @return whether song is liked or not
     */
    @Override
    public boolean isLiked(Song currentSong) {
        return currentSong.isLiked();
    }

    /**
     * @return list of all songs that have been liked
     */
    @Override
    public List<Song> getLikedSongs() {
        return Collections.unmodifiableList(likedSongs);
    }


}
