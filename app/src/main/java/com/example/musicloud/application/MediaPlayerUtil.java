package com.example.musicloud.application;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.TextUtils;

import com.example.musicloud.business.AccessSongs;
import com.example.musicloud.persistence.IPlayControlCallback;
import com.example.musicloud.persistence.IPlayStateCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Player tools class
 *
 * @author Admin
 */
public class MediaPlayerUtil implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, IPlayControlCallback {

    /*
     * Singleton object for music player
     */
    private static class SingletonHolder {
        private static final MediaPlayerUtil INSTANCE = new MediaPlayerUtil();
    }

    private final AccessSongs accessSongs = new AccessSongs() ;

    /*
     * Obtain static class
     *
     * @return Class object
     */
    public static synchronized MediaPlayerUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /*
     * MediaPlayer
     */
    private MediaPlayer mPlayer;

    /*
     * The value is 1000 milliseconds by default
     */
    private long mProgressIntervalTime = 1000;

    /*
     * Pause or not
     */
    private boolean isPaused;

    /*
     * ，The list of music you want to play
     */
    //private final List<String> mPlayMusicList = new ArrayList<>();
    private final List<String> mPlayMusicList = accessSongs.getSongNames();


    /*
     * The position that is playing music
     */
    private int mPlayingPosition = -1;


    /*
     * Listeners that need callback status
     */
    private final List<IPlayStateCallback> mPlayStateCallbackList = new ArrayList<>();

    /*
     * constructor
     */
    private MediaPlayerUtil() {
        mPlayer = new MediaPlayer();
        //Multiple listening implementations for music players
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
        mPlayer.setOnPreparedListener(this);
    }

    /*
     * Set the playback list
     *
     * @param musicList music list
     */
    public void setPlayMusicList(List<String> musicList) {
        mPlayMusicList.addAll(musicList);
    }

    /*
     * Set the playback location
     *
     * @param playingPosition Play position
     */
    public void setPlayingPosition(int playingPosition) {
        this.mPlayingPosition = playingPosition;
    }


    /*
     * Gets the position being played
     *
     * @return The position that is playing

    public int getPlayingPosition() {
        return mPlayingPosition;
    }
    */

    /**
     * play
     *
     * @return Whether the device is successfully played: true: The device is successfully played. false: fail;
     */
    @Override
    public boolean play() {
        if (mPlayMusicList.size() > 0 && mPlayingPosition >= 0) {
            //play music at pause status
            if (isPaused) {
                mPlayer.start();
                notifyPlay(mPlayMusicList.get(mPlayingPosition));
                return true;
            }
            try {
                mPlayer.reset();
                String assertMusicName = mPlayMusicList.get(mPlayingPosition);
                AssetFileDescriptor assetDescriptor = MyApp.getInstance().getApplicationContext().getAssets().openFd(assertMusicName + ".mp3");
                mPlayer.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
                mPlayer.prepare();
                mPlayer.start();
                notifyPlay(assertMusicName);
            } catch (IOException e) {
                notifyPlayFailed(mPlayMusicList.get(mPlayingPosition), 0, 0, e);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * play
     *
     * @param name The name of the music being played
     * @return Whether the device is successfully played: true: The device is successfully played. false: fail;
     */
    @Override
    public boolean play(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        isPaused = false;
        //If it is not in the playlist, add it, and play it
        if (!mPlayMusicList.contains(name)) {
            mPlayMusicList.add(name);
            mPlayingPosition = mPlayMusicList.size() - 1;
        }
        return play();
    }

    /**
     * Play the last song
     *
     * @return Whether the previous song is played successfully. true: successful. false: fail;
     */

    @Override
    public boolean playLast() {
        isPaused = false;
        if (mPlayMusicList.size() > 0 && mPlayingPosition >= 0) {
            //After we got to the first one in the last video, we just assigned the last one
            mPlayingPosition = mPlayingPosition - 1 < 0 ? mPlayMusicList.size() - 1 : mPlayingPosition - 1;
            if (play()) {
                notifyPlayLast(mPlayMusicList.get(mPlayingPosition));
                return true;
            }
        }
        return false;
    }

    /**
     * Play the next song
     *
     * @return Whether to play the next song successfully. true: successful. false: fail;
     */

    @Override
    public boolean playNext() {
        isPaused = false;
        if (mPlayMusicList.size() > 0 && mPlayingPosition >= 0) {
            //Next time we go to the last one, we just assign to the first one
            mPlayingPosition = mPlayingPosition + 1 >= mPlayMusicList.size() ? 0 : mPlayingPosition + 1;
            if (play()) {
                notifyPlayNext(mPlayMusicList.get(mPlayingPosition));
                return true;
            }
        }
        return false;
    }

    /**
     * pause
     *
     * @return Whether to suspend successfully: true: succeeded. false: fail;
     */
    @Override
    public boolean pause() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            isPaused = true;
            notifyPause(mPlayMusicList.get(mPlayingPosition));
            return true;
        }
        return false;
    }

    /**
     * Whether it is being played
     *
     * @return Is it playing? true is playing and false is paused
     */
    @Override
    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    /**
     * Get the playback progress
     *
     * @return The value of the playback progress
     */
    @Override
    public int getProgress() {
        return mPlayer.getCurrentPosition();
    }

    /**
     * Free the player's resources
     */
    @Override
    public void releasePlayer() {
        if (mPlayMusicList.size() > 0) {
            mPlayMusicList.clear();
        }
        mPlayer.reset();
        mPlayer.release();
        mPlayer = null;
    }

    /**
     * Register listening
     *
     * @param callback Status callback callback
     */
    public void registerCallback(IPlayStateCallback callback) {
        mPlayStateCallbackList.add(callback);
    }

    /**
     * Cancel listening
     *
     * @param callback Status callback callback
     */
    public void unregisterCallback(IPlayStateCallback callback) {
        mPlayStateCallbackList.remove(callback);
    }

    /**
     * Remove all listeners
     */
    public void removeCallbacks() {
        mPlayStateCallbackList.clear();
    }


    /**
     * Set the interval for the playback progress
     *
     * @param time time
     * @return class
     */
    public MediaPlayerUtil setProgressInterval(long time) {
        mProgressIntervalTime = time;
        return this;
    }

    /**
     * Release resources
     */
    public void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
        mPlayProgressTimeHandler.removeCallbacks(mPlayProgressTimeRunnable);
    }

    /**
     * Get player object
     *
     * @return MediaPlayer
     */
    public MediaPlayer getMediaPlayer() {
        return mPlayer;
    }

    /**
     * Playback progress refresh
     */
    private final Handler mPlayProgressTimeHandler = new Handler();
    private final Runnable mPlayProgressTimeRunnable = new Runnable() {
        @Override
        public void run() {
            mPlayProgressTimeHandler.removeCallbacks(this);
            if (mPlayer != null && mPlayer.isPlaying()) {
                notifyPlayProgress(mPlayMusicList.get(mPlayingPosition), 100 * mPlayer.getCurrentPosition() / mPlayer.getDuration());
            }
            mPlayProgressTimeHandler.postDelayed(this, mProgressIntervalTime);
        }
    };

    /**
     * Play the completed callback method
     *
     * @param mp MediaPlayer
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mPlayMusicList.size() > 0 && mPlayingPosition >= 0) {
            notifyPlayComplete(mPlayMusicList.get(mPlayingPosition));
            //下一曲到最后一个后，我们直接赋值到第一个
            mPlayingPosition = mPlayingPosition + 1 >= mPlayMusicList.size() ? 0 : mPlayingPosition + 1;
            play();
        }
    }

    /**
     * Incorrect callback was played
     *
     * @param mp    MediaPlayer
     * @param what  what the Message is
     * @param extra extra of Message
     * @return
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (mPlayMusicList.size() > 0 && mPlayingPosition >= 0) {
            notifyPlayFailed(mPlayMusicList.get(mPlayingPosition), what, extra, new Exception("MediaPlayer->play failed"));
        }
        return false;
    }

    /**
     * Player buffer finished callback
     *
     * @param mp MediaPlayer
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mPlayMusicList.size() > 0 && mPlayingPosition >= 0) {
            String name = mPlayMusicList.get(mPlayingPosition);
            try {
                mPlayer.start();
                mPlayProgressTimeHandler.postDelayed(mPlayProgressTimeRunnable, mProgressIntervalTime);
            } catch (Exception e) {
                notifyPlayFailed(name, 0, 0, new Exception("MediaPlayer->play failed"));
            }
            notifyPlayPrepare(name);
        }
    }

    /**
     * replay
     */
    public void replay() {
        mPlayer.seekTo(0);
    }

    /**
     * Notify all listeners to play the previous one
     *
     * @param name Song title
     */
    private void notifyPlayLast(String name) {
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onSwitchToLast(name);
            }
        }
    }

    /**
     * Notify all listeners to play the next one
     *
     * @param name Song title
     */
    private void notifyPlayNext(String name) {
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onSwitchToNext(name);
            }
        }
    }

    /**
     * Notify all listeners that buffering is complete
     *
     * @param name Song title
     */
    private void notifyPlayPrepare(String name) {
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onPrepare(name);
            }
        }
    }

    /**
     * Notify all listeners to start playing
     *
     * @param name Song title
     */
    private void notifyPlay(String name) {
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onPlay(name);
            }
        }
    }


    /**
     * Notify all listeners that the play is suspended
     *
     * @param name Song title
     */
    private void notifyPause(String name) {
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onPause(name);
            }
        }
    }


    /**
     * Notify all listeners that playback has failed
     *
     * @param name      Play a failed song
     * @param what      what the Message is
     * @param extra     extra of Message
     * @param exception Exception message
     */
    private void notifyPlayFailed(String name, int what, int extra, Exception exception) {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            isPaused = true;
        }
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onFailed(name, what, extra, exception);
            }
        }
    }

    /**
     * Notify all listeners to play the real-time progress
     *
     * @param name     Song title
     * @param progress Play schedule
     */
    private void notifyPlayProgress(String name, int progress) {
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onProgress(name, progress);
            }
        }
    }

    /**
     * Notify all listeners that play is complete
     *
     * @param name Song title
     */
    private void notifyPlayComplete(String name) {
        for (int i = 0; i < mPlayStateCallbackList.size(); i++) {
            IPlayStateCallback callback = mPlayStateCallbackList.get(i);
            if (callback != null) {
                callback.onComplete(name);
            }
        }
    }

}
