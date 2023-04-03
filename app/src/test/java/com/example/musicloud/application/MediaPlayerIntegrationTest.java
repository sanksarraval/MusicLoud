package com.example.musicloud.application;

import static org.junit.Assert.assertEquals;

import android.media.MediaPlayer;

import androidx.annotation.Nullable;

import com.example.musicloud.presentation.IPlayStateCallback;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MediaPlayerIntegrationTest {


    @Test
    public void testPlayMusic() {
        /*
         * Since the real MediaPlayerUtil has the Android object MediaPlayer in it, we're basically emulating the entire overlay behavior here
         */
        MediaPlayerUtil mediaPlayerUtil = new MediaPlayerUtil();
        //The track before track 3 is track 2, and the index position is 1 so the result is 1
        int pos = mediaPlayerUtil.getLastPlayingPosition(mediaPlayerUtil.getNextPlayingPosition(1));
        assertEquals(1, pos);
        System.out.println("And then the previous one on track three==" + pos);

        List<IPlayStateCallback> callbackList = new ArrayList<>();

        //Simulate three listeners
        callbackList.add(new TestCallback("listener01"));
        callbackList.add(new TestCallback("listener02"));
        callbackList.add(new TestCallback("listener03"));

        mediaPlayerUtil.notifyPlayPrepare(callbackList, "Alan Walker-Faded");
        //Here the simulation progress is 10
        for (int i = 1; i < 11; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Simulates the real-time playback progress of music
            mediaPlayerUtil.notifyPlayProgress(callbackList, "Alan Walker-Faded", i);
        }
        mediaPlayerUtil.notifyPlayComplete(callbackList, "Alan Walker-Faded");
    }


    static class MediaPlayerUtil {
        /**
         * The position of the previous song
         *
         * @param playingPosition Play position
         * @return The calculated play position
         */
        public int getLastPlayingPosition(int playingPosition) {
            int playListSize = 5;
            return playingPosition - 1 < 0 ? playListSize - 1 : playingPosition - 1;
        }

        /**
         * The position of the next song
         *
         * @param playingPosition Play position
         * @return The calculated play position
         */
        public int getNextPlayingPosition(int playingPosition) {
            int playListSize = 5;
            return playingPosition + 1 >= playListSize ? 0 : playingPosition + 1;
        }

        /**
         * ready to notification to all registered listeners
         *
         * @param callbackList listeners
         * @param name         music name
         */
        public void notifyPlayPrepare(List<IPlayStateCallback> callbackList, String name) {
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
        public void notifyPlayProgress(List<IPlayStateCallback> callbackList, String name, int progress) {
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
        public void notifyPlayComplete(List<IPlayStateCallback> callbackList, String name) {
            for (int i = 0; i < callbackList.size(); i++) {
                IPlayStateCallback callback = callbackList.get(i);
                if (callback != null) {
                    callback.onComplete(name);
                }
            }
        }
    }


    /**
     *
     */
    public static class TestCallback implements IPlayStateCallback {

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
