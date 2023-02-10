package com.example.musicloud.persistence;

import java.util.List;

import com.example.musicloud.objects.Song;

public interface SongPersistence {

    List<Song> getSongs();

    Song insertSong (Song currentSong);

    Song updateSong (Song currentSong);

    void deleteSong (Song currentSong);
}
