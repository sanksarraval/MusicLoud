package com.example.musicloud.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.musicloud.R;
import com.example.musicloud.business.AccessSongs;
import com.example.musicloud.objects.Song;

import java.util.List;

public class LikedActivity extends AppCompatActivity implements View.OnClickListener, IPlayStateCallback{

    private TextView tvName;
    private AppCompatImageView ivLast;
    private AppCompatImageView ivPlay;
    private AppCompatImageView ivNext;
    private AppCompatImageView ivReplay;
    private AppCompatImageView ivLike;
    private ProgressBar pbProgress;
    private AccessSongs songs = new AccessSongs();
    private List<Song> songList = songs.getSongs();
    private List<String> musicList = songs.getSongNames();
    private Song currentSong; // declare a field to hold the current song object
    private int currentPos;
    private List<Song> likedSongs = songs.getLikedSongs();

    /**
     * Creates the likes songs interface and keeps the state of the mediaplayer
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        MediaPlayerUtil mediaPlayerUtil = MediaPlayerUtil.getInstance();
        //Register for playback status listening
        mediaPlayerUtil.registerCallback(this);
        tvName = findViewById(R.id.tvName);
        ivLast = findViewById(R.id.ivLast);
        ivPlay = findViewById(R.id.ivPlay);
        ivNext = findViewById(R.id.ivNext);
        ivReplay = findViewById(R.id.ivReplay);
        pbProgress = findViewById(R.id.pbProgress);
        ivLike = findViewById(R.id.ivLike);

        //Loop for songs
        LinearLayout songLayout = findViewById(R.id.songs_liked);

        for (int i = 0; i < likedSongs.size(); i++) {
            Song song = likedSongs.get(i);
            int index = findPos(song); // get the index of the current song in likedSongs
            FrameLayout layout = (FrameLayout) getLayoutInflater().inflate(R.layout.liked_item, null);
            layout.setId(i);

            layout.setOnClickListener(view -> {
                // get the position of the clicked song item
                currentPos = index; // use the index to set the current position
                mediaPlayerUtil.setPlayingPosition(index);
                mediaPlayerUtil.play(songList.get(index).getSongName());
                setHeart(currentSong);

                // add click animation
                view.animate()
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setDuration(100)
                        .withEndAction(() -> view.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start())
                        .start();
            });

            TextView songNameTextView = layout.findViewById(R.id.song_name_textview2);
            songNameTextView.setText(song.getSongName());

            TextView artistTextView = layout.findViewById(R.id.artist_textview2);
            artistTextView.setText(song.getArtist());

            ImageView imageView = layout.findViewById(R.id.song_button2);
            String curr = song.getImgID();
            imageView.setBackgroundResource(getResources().getIdentifier(curr,"drawable", getApplicationContext().getPackageName()));

            songLayout.addView(layout);
        }



        //Set play source
        Intent intent = getIntent();
        currentPos = mediaPlayerUtil.getPlayingPosition();
        int position = intent.getIntExtra("position", currentPos);
        mediaPlayerUtil.setPlayMusicList(musicList);
        mediaPlayerUtil.setPlayingPosition(position);
        setMusicInfo(musicList.get(position));
        currentSong = songList.get(position);
        setHeart(currentSong);


        ivLast.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivReplay.setOnClickListener(this);
    }

    /**
     * finds the position of the song in the songList
     *
     * @param current
     * @return position of song in list
     */
    public int findPos(Song current){
        int pos = 0;
        for(int i = 0; i < songList.size(); i++){
            if(songList.get(i).getSongName().equals(current.getSongName())){
                pos = i;
            }
        }
        return pos;
    }

    /**
     * goes back to main page when back button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LikedActivity.this, PlayActivity.class);
        LikedActivity.this.startActivity(intent);
    }
    /**
     * Switch to the previous song
     *
     * @param last Music name
     */
    @Override
    public void onSwitchToLast(@Nullable String last) {
        setMusicInfo(last);
    }

    /**
     * Switch to the next song
     *
     * @param next Music name
     */
    @Override
    public void onSwitchToNext(@Nullable String next) {
        setMusicInfo(next);
    }

    /**
     * Buffer reserve
     *
     * @param name Music name
     */
    @Override
    public void onPrepare(@Nullable String name) {
        pbProgress.setProgress(0);
    }

    /**
     * Play pause
     *
     * @param name Music name
     */
    @Override
    public void onPause(@Nullable String name) {
        setMusicInfo(name);
    }

    /**
     * Start playing
     *
     * @param name Music name
     */
    @Override
    public void onPlay(@Nullable String name) {
        setMusicInfo(name);
    }

    /**
     * Play failure
     *
     * @param name      Music name
     * @param what
     * @param extra
     * @param exception
     */
    @Override
    public void onFailed(@Nullable String name, int what, int extra, Exception exception) {
        setMusicInfo(name);
        exception.printStackTrace();
    }

    /**
     * Play schedule
     *
     * @param name     Music name
     * @param progress 进度
     */
    @Override
    public void onProgress(@Nullable String name, int progress) {
        pbProgress.setProgress(progress);
    }

    /**
     * Play completed
     *
     * @param name Music name
     */
    @Override
    public void onComplete(@Nullable String name) {
        pbProgress.setProgress(100);
    }

    /**
     * Play completed
     *
     * @param name Music name
     * @param mp   player
     */
    @Override
    public void onSeekComplete(@Nullable String name, MediaPlayer mp) {
        pbProgress.setProgress(100);
    }

    /**
     * Set music related information and ICONS
     *
     * @param name music name
     */
    protected void setMusicInfo(String name) {
        tvName.setText(name);
        ivPlay.setImageResource(MediaPlayerUtil.getInstance().isPlaying() ? R.mipmap.pause : R.mipmap.play);

        // find the current song object in the songList
        for (Song song : songList) {
            if (song.getSongName().equals(name)) {
                currentSong = song;
                break;
            }
        }
    }

    /**
     * likes the song in the database
     *
     * @param current
     */
    protected void setLikedInfo(@NonNull Song current){
        boolean liked = songs.isLiked(current);
        if(!liked){
            songs.likeSong(current);
        } else {
            songs.unlikeSong(current);
        }
        setHeart(current);
        likedSongs = songs.getLikedSongs();
        for(int i = 0; i < likedSongs.size(); i++){
            System.out.println(likedSongs.get(i));
        }
    }

    /**
     * sets the image according to the song if it's liked or not
     *
     * @param current
     */
    protected void setHeart(@NonNull Song current){
        boolean liked = songs.isLiked(current);
        if(!liked){
            ivLike.setImageResource(R.mipmap.openheart);
        } else {
            ivLike.setImageResource(R.mipmap.heart);
        }
    }

    /**
     * what happens when buttons are clicked
     *
     * @param view
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        MediaPlayerUtil mediaPlayerUtil = MediaPlayerUtil.getInstance();
        switch (view.getId()) {
            case R.id.ivLast:
                //Click on the previous song
                mediaPlayerUtil.playLast();
                setHeart(currentSong);
                currentPos--;
                break;
            case R.id.ivPlay:
                //Play or pause
                if (mediaPlayerUtil.isPlaying()) {
                    mediaPlayerUtil.pause();
                } else {
                    mediaPlayerUtil.play();
                }
                break;
            case R.id.ivNext:
                //Click on the next song
                mediaPlayerUtil.playNext();
                setHeart(currentSong);
                currentPos++;
                break;
            case R.id.ivReplay:
                //Hit replay
                if (mediaPlayerUtil.isPlaying()) {
                    AlertDialog alertDialog = new AlertDialog.Builder(LikedActivity.this)
                            .setTitle("Reminder")
                            .setMessage(getResources().getString(R.string.str_replay_msg))
                            .setPositiveButton("Sure", (dialog, which) -> {
                                dialog.dismiss();
                                mediaPlayerUtil.replay();
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                            .create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                } else {
                    mediaPlayerUtil.replay();
                    mediaPlayerUtil.play();
                }
                break;
            default:
        }
    }

    /**
     * likes/unlikes the song when like button is clicked
     * @param v
     */
    public void buttonLikeClick(View v){
        setLikedInfo(currentSong);
    }

    /**
     * resets media player when app is closed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Cancel playback status listening
        MediaPlayerUtil.getInstance().unregisterCallback(this);
    }

}
