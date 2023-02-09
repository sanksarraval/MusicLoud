package com.example.musicloud;


import android.media.MediaPlayer;

import androidx.annotation.Nullable;

/**
 * ，Playback status interface callback
 *
 * @author Admin
 */
public interface IPlayStateCallback {

    /**
     * Switch to the previous song
     *
     * @param last Music name
     */
    void onSwitchToLast(@Nullable String last);

    /**
     * Switch to the next song
     *
     * @param next Music name
     */
    void onSwitchToNext(@Nullable String next);

    /**
     * Buffer reserve
     *
     * @param name Music name
     */
    void onPrepare(@Nullable String name);

    /**
     * Play pause
     *
     * @param name Music name
     */
    void onPause(@Nullable String name);

    /**
     * Start playing
     *
     * @param name Music name
     */
    void onPlay(@Nullable String name);

    /**
     * Play failure
     *
     * @param name      Play a failed song
     * @param what      what the Message is
     * @param extra     extra of Message
     * @param exception Exception message
     */
    void onFailed(@Nullable String name, int what, int extra, Exception exception);

    /**
     * Play schedule
     *
     * @param name     Music name
     * @param progress 进度
     */
    void onProgress(@Nullable String name, int progress);

    /**
     * Play completed
     *
     * @param name Music name
     */
    void onComplete(@Nullable String name);

    /**
     * Play completed
     *
     * @param name Music name
     * @param mp   player
     */
    void onSeekComplete(@Nullable String name, MediaPlayer mp);
}
