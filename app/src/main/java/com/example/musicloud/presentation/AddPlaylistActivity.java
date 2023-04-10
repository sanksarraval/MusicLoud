package com.example.musicloud.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicloud.R;
import com.example.musicloud.business.AccessPlaylist;
import com.example.musicloud.business.AccessSP;
import com.example.musicloud.business.AccessSongs;
import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.Song;

import java.util.ArrayList;
import java.util.List;

public class AddPlaylistActivity extends AppCompatActivity implements View.OnClickListener{


    private AccessSongs songs = new AccessSongs();
    private List<Song> songList = songs.getSongs();

    private List<Song> clicked = new ArrayList<>();
    private AccessSP allPairs = new AccessSP();
    private AccessPlaylist playlists = new AccessPlaylist();

    /**
     *  creates the add playlist interface with all the song buttons and front-end
     * @param savedInstanceState
     */
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

    /**
     *
     * @param v
     */
    public void onClick(View v){
        //does something when a button is clicked, all buttons covered
        //in onCreate, so nothing here, part of interface
    }

    /**
     * When the save playlist button is clicked, it stores all the clicked songs
     * in the database tables and takes back to playActivity
     * @param v
     */
    public void savePlaylistButton(View v){
        EditText inputBox = findViewById(R.id.new_play_name);
        String playlistName = inputBox.getText().toString();
        boolean isValid = playlists.canAddPlaylist(clicked.size(), playlistName);

        if(isValid){
            //only make playlist if at least 1 song selected
            playlists.insertPlaylist(new Playlist(playlistName));
            int pId = playlists.getPlaylistId(playlistName);

            for(int i=0;i<clicked.size();i++){
                int sId = clicked.get(i).getId();
                allPairs.insertData(pId, playlistName, sId, clicked.get(i).getSongName());
            }

            Intent intent = new Intent(AddPlaylistActivity.this, PlayActivity.class );
            AddPlaylistActivity.this.startActivity(intent);
        }
        else if(clicked.size() == 0){
            //toast message to choose right values
            Toast.makeText(AddPlaylistActivity.this, "Please Select at least one song", Toast.LENGTH_SHORT).show();
        }
        else if(playlistName.trim().equals("")){
            Toast.makeText(AddPlaylistActivity.this, "Playlist Name cannot be Empty!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AddPlaylistActivity.this, "Playlist with this name Exists, Please enter a new name!!", Toast.LENGTH_SHORT).show();
        }

    }
}