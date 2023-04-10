package com.example.musicloud.presentation;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.musicloud.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RunPlaylistActivityTest {

    @Rule
    public ActivityScenarioRule<AddPlaylistActivity> activityRule = new ActivityScenarioRule<>(AddPlaylistActivity.class);

    @Test
    public void testPlayActivity() throws InterruptedException {

        //making first playlist
        //click on save playlist button and wait
        onView(withId(R.id.save_playlist_button)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);

        //select one song and save
        onView(withId(R.id.songs_to_add)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);
        onView(withId(R.id.save_playlist_button)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);


        //type playlist name as play1, save and wait
        onView(withId(R.id.new_play_name)).perform(typeTextIntoFocusedView("noway110"));
        SystemClock.sleep(1000*1);
        onView(withId(R.id.save_playlist_button)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);



        //making another playlist
        onView(withId(R.id.add_playlist_button)).perform(closeSoftKeyboard(), click());


        onView(withId(R.id.songs_to_add)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);


        onView(withId(R.id.new_play_name)).perform(typeTextIntoFocusedView("noway110"));
        SystemClock.sleep(1000*1);


        onView(withId(R.id.save_playlist_button)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);
        onView(withId(R.id.save_playlist_button)).perform(closeSoftKeyboard(), click());

        onView(withId(R.id.new_play_name)).perform(clearText());
        onView(withId(R.id.new_play_name)).perform(typeTextIntoFocusedView("noway111"));
        SystemClock.sleep(1000*1);

        onView(withId(R.id.save_playlist_button)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);


        //time to play and remove playlist
        onView(withId(R.id.playlist)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000 * 2);

        //click on a song
        onView(withId(R.id.playlist_song)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000*2);

        //remove the playlist
        onView(withId(R.id.remove_playlist_button)).perform(closeSoftKeyboard(), click());
        SystemClock.sleep(1000*4);

        onView(withId(R.id.add_playlist_button)).perform(closeSoftKeyboard());
        SystemClock.sleep(1000*4);

    }
}