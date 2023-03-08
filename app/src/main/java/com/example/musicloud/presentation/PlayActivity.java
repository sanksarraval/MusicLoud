package com.example.musicloud.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, IPlayStateCallback {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        //Loop
        LinearLayout songLayout = findViewById(R.id.song);

        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.song_item, null);
            layout.setId(i);

            Button button = layout.findViewById(R.id.song_button);
            button.setId(View.generateViewId());
            button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
            int finalI = i;
            button.setOnClickListener(view -> {
                // get the position of the clicked song item
                mediaPlayerUtil.setPlayingPosition(finalI);
                mediaPlayerUtil.play(songList.get(finalI).getSongName());
                setHeart(currentSong);
            });
            TextView songNameTextView = layout.findViewById(R.id.song_name_textview);
            songNameTextView.setText(song.getSongName());

            TextView artistTextView = layout.findViewById(R.id.artist_textview);
            artistTextView.setText(song.getArtist());

            songLayout.addView(layout);
        }

        //Set play source
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        mediaPlayerUtil.setPlayMusicList(musicList);
        mediaPlayerUtil.setPlayingPosition(position);
        setMusicInfo(musicList.get(position));

        ivLast.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivReplay.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        // Navigate to the Home screen of your app
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
    private void setMusicInfo(String name) {
        tvName.setText(name);
        ivPlay.setImageResource(MediaPlayerUtil.getInstance().isPlaying() ? R.mipmap.pause : R.mipmap.play);

        // find the current song object in the songList
        for (Song song : songList) {
            if (song.getSongName().equals(name)) {
                currentSong = song;
                System.out.println(currentSong.getSongName());
                break;
            }
        }
    }

    private void setLikedInfo(@NonNull Song current){
        boolean liked = songs.isLiked(current);
        if(!liked){
            songs.likeSong(current);
        } else {
            songs.unlikeSong(current);
        }
        setHeart(current);
    }

    private void setHeart(@NonNull Song current){
        boolean liked = songs.isLiked(current);
        if(!liked){
            ivLike.setImageResource(R.mipmap.openheart);
        } else {
            ivLike.setImageResource(R.mipmap.heart);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        MediaPlayerUtil mediaPlayerUtil = MediaPlayerUtil.getInstance();
        switch (view.getId()) {
            case R.id.ivLast:
                //Click on the previous song
                mediaPlayerUtil.playLast();
                setHeart(currentSong);
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
                break;
            case R.id.ivReplay:
                //Hit replay
                if (mediaPlayerUtil.isPlaying()) {
                    AlertDialog alertDialog = new AlertDialog.Builder(PlayActivity.this)
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

    public void buttonLikeClick(View v){
        setLikedInfo(currentSong);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Cancel playback status listening
        MediaPlayerUtil.getInstance().unregisterCallback(this);
    }
}