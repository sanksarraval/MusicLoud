package com.example.musicloud.objects;

import androidx.annotation.NonNull;

public class Song {
    private int id;
    private final String songName;
    private final String artist;
    private final String albumName;
    private boolean liked;

    public Song(String songName, String artist) {
        this.songName = songName;
        this.artist = artist;
        albumName = "na";
        liked = false;
    }

    public Song (String songName, String artist, String albumName) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
    }

    public Song (String songName, String artist, String albumName, boolean liked) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        this.liked = liked;
    }

    public Song(int id, String songName, String artist, String albumName, boolean liked) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        this.liked = liked;
    }

    public Song(int id) {
        this.id = id;
        this.songName = null;
        this.artist = null;
        this.albumName = "na";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked() {
        this.liked = !liked;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Song{");
        if (id>0){
            s.append("id ='").append(id).append("', ");
        }
        if (songName != null){
            s.append("songName='").append(songName).append("', ");
        }
        if (artist != null){
            s.append("artist='").append(artist).append("', ");
        }
        if (albumName != null){
            s.append("album name='").append(albumName).append("', ");
        }
        s.append("liked='").append(liked).append("'}");

        return s.toString();
    }
}
