package com.example.musicloud.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.musicloud.MediaPlayerUtil;
import com.example.musicloud.R;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, IPlayStateCallback {

    private TextView tvName;
    private AppCompatImageView ivLast;
    private AppCompatImageView ivPlay;
    private AppCompatImageView ivNext;
    private AppCompatImageView ivReplay;
    private ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        MediaPlayerUtil mediaPlayerUtil = MediaPlayerUtil.getInstance();
        //Register for playback status listening
        mediaPlayerUtil.registerCallback(this);
        tvName = findViewById(R.id.tvName);
        ivLast = findViewById(R.id.ivLast);
        ivPlay = findViewById(R.id.ivPlay);
        ivNext = findViewById(R.id.ivNext);
        ivReplay = findViewById(R.id.ivReplay);
        pbProgress = findViewById(R.id.pbProgress);
        //Prepare music list data
        List<String> musicList = new ArrayList<>();
        String name = "Guns N' Roses-Don't Cry";

        musicList.add("Guns N' Roses-Don't Cry");
        musicList.add("Alan Walker-Faded");
        musicList.add("Martin Garrix&David Guetta&Jamie Scott&Romy Dya-So Far Away");
        musicList.add("Olly Murs-That Girl");
        musicList.add("Tysm-Normal No More(Explicit)");

        //Set play source
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);


        if(position==1){
            mediaPlayerUtil.setPlayMusicList(musicList);
            mediaPlayerUtil.setPlayingPosition(0);
            name = musicList.get(0);
            setMusicInfo(name);
            mediaPlayerUtil.play(name);
        }
        else if(position==2){
            mediaPlayerUtil.setPlayMusicList(musicList);
            mediaPlayerUtil.setPlayingPosition(1);
            name = musicList.get(1);
            setMusicInfo(name);
            mediaPlayerUtil.play(name);
        }
        else if(position==3){
            mediaPlayerUtil.setPlayMusicList(musicList);
            mediaPlayerUtil.setPlayingPosition(2);
            name = musicList.get(2);
            setMusicInfo(name);
            mediaPlayerUtil.play(name);
        }
        else if(position==4){
            mediaPlayerUtil.setPlayMusicList(musicList);
            mediaPlayerUtil.setPlayingPosition(3);
            name = musicList.get(3);
            setMusicInfo(name);
            mediaPlayerUtil.play(name);
        }
        else if(position==5){
            mediaPlayerUtil.setPlayMusicList(musicList);
            mediaPlayerUtil.setPlayingPosition(4);
            name = musicList.get(4);
            setMusicInfo(name);
            mediaPlayerUtil.play(name);
        }

        tvName.setText(name);
        ivLast.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivReplay.setOnClickListener(this);

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
        ivPlay.setImageResource(MediaPlayerUtil.getInstance().isPlaying() ? R.mipmap.ic_pause : R.mipmap.ic_play);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        MediaPlayerUtil mediaPlayerUtil = MediaPlayerUtil.getInstance();
        switch (view.getId()) {
            case R.id.ivLast:
                //Click on the previous song
                mediaPlayerUtil.playLast();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Cancel playback status listening
        MediaPlayerUtil.getInstance().unregisterCallback(this);
    }


}