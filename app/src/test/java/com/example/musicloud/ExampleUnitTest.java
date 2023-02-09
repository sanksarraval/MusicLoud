package com.example.musicloud;

import android.media.MediaPlayer;

import androidx.annotation.Nullable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        //***************************************The positional logic of the previous one***************************************


        // Suppose the length of the list is 5
        // Location logic processing of the previous song
        // The track before the third is the second track. The index position is 1 so the result is 1
        System.out.println("The last track of track three==" + getLastPlayingPosition(2));
        // The one before the first song is the last song, and the index position is 4 so it's 4
        System.out.println("The previous track of the first one==" + getLastPlayingPosition(0));
        /*
         * Print result:
         * The previous song on track 3 ==1
         * The previous song of the first song ==4
         *
         *
         */


        //****************************************The logical processing of the position in the next song**************************************


        // Location logic processing for the next song
        // Track 3 is next to track 2. The index position is 2, so the result is 2
        System.out.println("The next track of track two==" + getNextPlayingPosition(1));
        //The track before track 3 is track 2, and the index position is 1 so the result is 1
        System.out.println("And then the previous one on track three==" + getLastPlayingPosition(getNextPlayingPosition(1)));
        //The next track after the last track is the first track, and the index position is 0 so the result is 0
        System.out.println("The next track to the last one==" + getNextPlayingPosition(4));
        /*
         * Print result:
         * The next song of track 2 ==2
         * And then the previous song on track 3 ==1
         * The next song of the last song ==0
         *
         */


        //*****************************************Music callback logic processing********************************************************


        //Music callback logic processing
        List<IPlayStateCallback> callbackList = new ArrayList<>();
        //Simulate three listeners
        callbackList.add(new TestCallback("listener01"));
        callbackList.add(new TestCallback("listener02"));
        callbackList.add(new TestCallback("listener03"));
        //Notify all listeners when the analog music is buffered
        notifyPlayPrepare(callbackList, "Alan Walker-Faded");
        //Here the simulation progress is 10
        for (int i = 1; i < 11; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Simulates the real-time playback progress of music
            notifyPlayProgress(callbackList, "Alan Walker-Faded", i);
        }
        //The simulation music is played
        notifyPlayComplete(callbackList, "Alan Walker-Faded");
    }

    /**
     * The position of the previous song
     *
     * @param playingPosition Play position
     * @return The calculated play position
     */
    private int getLastPlayingPosition(int playingPosition) {
        int playListSize = 5;
        return playingPosition - 1 < 0 ? playListSize - 1 : playingPosition - 1;
    }

    /**
     * The position of the next song
     *
     * @param playingPosition Play position
     * @return The calculated play position
     */
    private int getNextPlayingPosition(int playingPosition) {
        int playListSize = 5;
        return playingPosition + 1 >= playListSize ? 0 : playingPosition + 1;
    }

    /**
     * 准备完成通知所有的注册监听
     *
     * @param callbackList 监听者
     * @param name         歌曲名称
     */
    private void notifyPlayPrepare(List<IPlayStateCallback> callbackList, String name) {
        for (int i = 0; i < callbackList.size(); i++) {
            IPlayStateCallback callback = callbackList.get(i);
            if (callback != null) {
                callback.onPrepare(name);
            }
        }
    }

    /**
     * Real-time progress notification to all registered listeners
     *
     * @param callbackList listeners
     * @param name         song title
     * @param progress     Play schedule
     */
    private void notifyPlayProgress(List<IPlayStateCallback> callbackList, String name, int progress) {
        for (int i = 0; i < callbackList.size(); i++) {
            IPlayStateCallback callback = callbackList.get(i);
            if (callback != null) {
                callback.onProgress(name, progress);
            }
        }
    }

    /**
     * Notify all registered listeners when playback is complete
     *
     * @param callbackList listeners
     * @param name         song title
     */
    private void notifyPlayComplete(List<IPlayStateCallback> callbackList, String name) {
        for (int i = 0; i < callbackList.size(); i++) {
            IPlayStateCallback callback = callbackList.get(i);
            if (callback != null) {
                callback.onComplete(name);
            }
        }
    }

    /**
     * Listener class
     */
    static class TestCallback implements IPlayStateCallback {

        private final String callbackName;

        public TestCallback(String callbackName) {
            this.callbackName = callbackName;
        }

        @Override
        public void onSwitchToLast(@Nullable String last) {
            System.out.println(callbackName + "==onSwitchToLast==" + last);
        }

        @Override
        public void onSwitchToNext(@Nullable String next) {
            System.out.println(callbackName + "==onSwitchToNext==" + next);
        }

        @Override
        public void onPrepare(@Nullable String name) {
            System.out.println(callbackName + "==onPrepare==" + name);
        }

        @Override
        public void onPause(@Nullable String name) {

        }

        @Override
        public void onPlay(@Nullable String name) {

        }

        @Override
        public void onFailed(@Nullable String name, int what, int extra, Exception exception) {

        }

        @Override
        public void onProgress(@Nullable String name, int progress) {
            System.out.println(callbackName + "==onProgress==" + name + "==progress==" + progress);
        }

        @Override
        public void onComplete(@Nullable String name) {
            System.out.println(callbackName + "==onComplete==" + name);
        }

        @Override
        public void onSeekComplete(@Nullable String name, MediaPlayer mp) {

        }
    }
}