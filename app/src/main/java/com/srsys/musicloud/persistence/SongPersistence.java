package com.srsys.musicloud.persistence;

import java.util.List;

import com.srsys.musicloud.objects.Song;

public interface SongPersistence {

    List<Song> getAllSongs();
    //alpha order by artist then song

    Song getSong (int i);

    int getSize ();

    Song insertSong (Song currentSong);

    Song updateSong (Song currentSong);

    void deleteSong (Song currentSong);

    List<String> allSongNames();
}
