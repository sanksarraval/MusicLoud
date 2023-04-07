package com.example.musicloud.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.musicloud.R;
import com.example.musicloud.business.AccessPlaylist;
import com.example.musicloud.business.AccessSP;
import com.example.musicloud.business.AccessSongs;
import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SongPersistence;

import java.util.ArrayList;
import java.util.List;

public class AddPlaylistActivity extends AppCompatActivity implements View.OnClickListener{


    private AccessSongs songs = new AccessSongs();
    private List<Song> songList = songs.getSongs();

    private List<Song> clicked = new ArrayList<>();
    private AccessSP allPairs = new AccessSP();
    private AccessPlaylist playlists = new AccessPlaylist();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playlist);


        //Loop for songs
        LinearLayout songLayout = findViewById(R.id.songs_to_add);

        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);

            FrameLayout layout = (FrameLayout) getLayoutInflater().inflate(R.layout.liked_item, null);
            layout.setId(i);

            TextView songNameTextView = layout.findViewById(R.id.song_name_textview2);
            songNameTextView.setText(song.getSongName());

            TextView artistTextView = layout.findViewById(R.id.artist_textview2);
            artistTextView.setText(song.getArtist());



            layout.setOnClickListener(view->{
                if(clicked.contains(song)){
                    layout.setBackgroundColor(Color.parseColor("#3d036b"));
                    clicked.remove(song);
                }
                else{
                    layout.setBackgroundColor(Color.parseColor("#A252E1"));
                    clicked.add(song);
                }
            });


            songLayout.addView(layout);
        }
    }


    public void onClick(View v){

    }

    public void savePlaylistButton(View v){
//        EditText inputBox = findViewById(R.id.new_play_name);
//        String playlistName = inputBox.getText().toString();
//
//        if(clicked.size()>0){
//            //only make playlist if at least 1 song selected
//            playlists.insertPlaylist(new Playlist(playlistName));
//
//            for(int i=0;i<clicked.size();i++){
//                allPairs.
//            }
//        }
    }
}