package com.example.musicloud.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.musicloud.presentation.IPlayStateCallback;
import com.example.musicloud.presentation.MediaPlayerUtil;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MediaPlayerTest {

    /**
     * Use @Mock annotations
     */
    @Mock
    private MediaPlayerUtil mMediaPlayerUtil;

    //Use @Rule annotations
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testNextSong() {
        //****************************************The logical processing of the position in the next song**************************************

        // Location logic processing for the next song
        //Simulate the real computational logic in the tool class
        doAnswer(invocation -> {
            int playingPosition = invocation.getArgument(0);
            //Let's say the number of music is 5
            int playListSize = 5;
            return playingPosition + 1 >= playListSize ? 0 : playingPosition + 1;
        }).when(mMediaPlayerUtil).getNextPlayingPosition(anyInt());
        // Track 3 is next to track 2. The index position is 2, so the result is 2
//        when(mMediaPlayerUtil.getNextPlayingPosition(1)).thenReturn(2);
        assertEquals(mMediaPlayerUtil.getNextPlayingPosition(1), 2);
        System.out.println("The next track of track two==" + mMediaPlayerUtil.getNextPlayingPosition(1));
        //The next track after the last track is the first track, and the index position is 0 so the result is 0
//        when(mMediaPlayerUtil.getNextPlayingPosition(4)).thenReturn(0);
        assertEquals(mMediaPlayerUtil.getNextPlayingPosition(4), 0);
        System.out.println("The next track to the last one==" + mMediaPlayerUtil.getNextPlayingPosition(4));
        /*
         * Print result:
         * The next song of track 2 ==2
         * And then the previous song on track 3 ==1
         * The next song of the last song ==0
         *
         */
    }


    @Test
    public void testPreviousSong() {
        //***************************************The positional logic of the previous one***************************************

        // Suppose the length of the list is 5
        // Location logic processing of the previous song
        //Simulate the real computational logic in the tool class
        doAnswer(invocation -> {
            int playingPosition = invocation.getArgument(0);
            //Let's say the number of music is 5
            int playListSize = 5;
            return playingPosition - 1 < 0 ? playListSize - 1 : playingPosition - 1;
        }).when(mMediaPlayerUtil).getLastPlayingPosition(anyInt());
        // The track before the third is the second track. The index position is 1 so the result is 1
//        when(mMediaPlayerUtil.getLastPlayingPosition(2)).thenReturn(1);
        assertEquals(mMediaPlayerUtil.getLastPlayingPosition(2), 1);
        System.out.println("The last track of track three==" + mMediaPlayerUtil.getLastPlayingPosition(2));
        // The one before the first song is the last song, and the index position is 4 so it's 4
//        when(mMediaPlayerUtil.getLastPlayingPosition(0)).thenReturn(4);
        assertEquals(mMediaPlayerUtil.getLastPlayingPosition(0), 4);
        System.out.println("The previous track of the first one==" + mMediaPlayerUtil.getLastPlayingPosition(0));
        /*
         * Print result:
         * The previous song on track 3 ==1
         * The previous song of the first song ==4
         *
         */
    }

    @Test
    public void testPlayCallBack() {
        //*****************************************Music callback logic processing********************************************************
        // List is an interface, not a concrete class, so you can't instantiate it directly, so you need to use ArrayList
        //   @Mock creates a Mock object (Mock) and @Spy creates the real ArrayList object (Spy)
//        mCallbackList=  Mockito.spy(new ArrayList<>());3
        List<IPlayStateCallback> callbackList = mock(List.class);

        //Simulate three listeners
        IPlayStateCallback mockCallback1 = mock(IPlayStateCallback.class);
        IPlayStateCallback mockCallback2 = mock(IPlayStateCallback.class);
        IPlayStateCallback mockCallback3 = mock(IPlayStateCallback.class);

        callbackList.add(mockCallback1);
        //Verify that the add method is executed
        verify(callbackList).add(mockCallback1);
        callbackList.add(mockCallback2);
        callbackList.add(mockCallback3);

        //Verify that ArrayList's add method is called three times and its length is equal to 3
        verify(callbackList, times(3)).add(any());

        //Returns 3 if the length of the collection is called
        Mockito.when(callbackList.size()).thenReturn(3);

        //If we call the get method, we return the corresponding object
        when(callbackList.get(anyInt())).thenAnswer(invocation -> {
            int index = invocation.getArgument(0);
            IPlayStateCallback callback;
            switch (index) {
                default:
                case 0:
                    callback = mockCallback1;
                    break;
                case 1:
                    callback = mockCallback2;
                    break;
                case 2:
                    callback = mockCallback3;
                    break;
            }
            return callback;
        });

//      The Mock call to notifyPlayPrepare is the operation we simulate
        doAnswer(invocation -> {
            for (int i = 0; i < callbackList.size(); i++) {
                IPlayStateCallback callback = callbackList.get(i);
                if (callback != null) {
                    callback.onPrepare(invocation.getArgument(0));
                }
            }
            return null;
        }).when(mMediaPlayerUtil).notifyPlayPrepare(anyString());
        //Notify all listeners when the analog music is buffered
        mMediaPlayerUtil.notifyPlayPrepare("Alan Walker-Faded");
        Mockito.verify(mMediaPlayerUtil, times(1)).notifyPlayPrepare("Alan Walker-Faded");
        // Verify that the expected output was printed
        Mockito.verify(mockCallback1, times(1)).onPrepare("Alan Walker-Faded");
        Mockito.verify(mockCallback2, times(1)).onPrepare("Alan Walker-Faded");
        Mockito.verify(mockCallback3, times(1)).onPrepare("Alan Walker-Faded");

        //Simulated playback progress callback
        doAnswer(invocation -> {
            for (int i = 0; i < callbackList.size(); i++) {
                IPlayStateCallback callback = callbackList.get(i);
                if (callback != null) {
                    callback.onProgress(invocation.getArgument(0), invocation.getArgument(1));
                }
            }
            return null;
        }).when(mMediaPlayerUtil).notifyPlayProgress(anyString(), anyInt());
        //Here the simulation progress is 10
        for (int i = 1; i < 11; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Simulates the real-time playback progress of music
            mMediaPlayerUtil.notifyPlayProgress("Alan Walker-Faded", i);
            //Check the number of callback times for each progress
            verify(mMediaPlayerUtil, times(1)).notifyPlayProgress("Alan Walker-Faded", i);
            verify(mockCallback1, times(1)).onProgress("Alan Walker-Faded", i);
            verify(mockCallback2, times(1)).onProgress("Alan Walker-Faded", i);
            verify(mockCallback3, times(1)).onProgress("Alan Walker-Faded", i);
        }
        //Total number of progress callbacks
        verify(mMediaPlayerUtil, times(10)).notifyPlayProgress(anyString(), anyInt());
        verify(mockCallback1, times(10)).onProgress(anyString(), anyInt());
        verify(mockCallback2, times(10)).onProgress(anyString(), anyInt());
        verify(mockCallback3, times(10)).onProgress(anyString(), anyInt());
        //The simulation music is played
        //Simulated playback completed callback
        doAnswer(invocation -> {
            for (int i = 0; i < callbackList.size(); i++) {
                IPlayStateCallback callback = callbackList.get(i);
                if (callback != null) {
                    callback.onComplete(invocation.getArgument(0));
                }
            }
            return null;
        }).when(mMediaPlayerUtil).notifyPlayComplete(anyString());
        mMediaPlayerUtil.notifyPlayComplete( "Alan Walker-Faded");
        verify(mockCallback1, times(1)).onComplete("Alan Walker-Faded");
        verify(mockCallback2, times(1)).onComplete("Alan Walker-Faded");
        verify(mockCallback3, times(1)).onComplete("Alan Walker-Faded");
    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

}
