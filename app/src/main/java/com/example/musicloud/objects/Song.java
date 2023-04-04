package com.example.musicloud.objects;

import androidx.annotation.NonNull;

public class Song {

    private int id;

    private final String songName;

    private final String artist;

    private final String albumName;

    private final String imgID;

    /**
     * boolean indicating if song has been liked
     */
    private boolean liked;

    /**
     * constructor
     */
    public Song(String songName, String artist) {
        this.songName = songName;
        this.artist = artist;
        albumName = "na";
        imgID = "na";
        liked = false;
    }

    /**
     * constructor
     */
    public Song (String songName, String artist, String albumName) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        imgID = "na";
    }

    /**
     * constructor
     */
    public Song (String songName, String artist, String albumName, String imgID, boolean liked) {
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        this.imgID = imgID;
        this.liked = liked;
    }

    /**
     * constructor
     */
    public Song(int id, String songName, String artist, String albumName, String imgID, boolean liked) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.albumName = albumName;
        this.imgID = imgID;
        this.liked = liked;
    }

    /**
     * constructor
     */
    public Song(int id) {
        this.id = id;
        this.songName = null;
        this.artist = null;
        this.imgID = "na";
        this.albumName = "na";
    }

    /**
     * @return id of song
     */
    public int getId() {
        return id;
    }

    /**
     * sets id to int passed
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return song name
     */
    public String getSongName() {
        return songName;
    }

    /**
     * @return song's artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @return album name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * @return image id
     */
    public String getImgID() {return imgID; }
    /**
     * @return whether song is liked or not
     */
    public boolean isLiked() {
        return liked;
    }

    /**
     * toggles liked boolean
     */
    public void setLiked() {
        this.liked = !liked;
    }

    /**
     * toString for Song object
     */
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
        if (imgID != null){
            s.append("image id='").append(imgID).append("', ");
        }
        s.append("liked='").append(liked).append("'}");

        return s.toString();
    }
}
