package com.example.musicloud.presentation;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import android.os.SystemClock;
import android.view.View;
import android.widget.AutoCompleteTextView;

import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.musicloud.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PlayActivityTest {

    @Rule
    public ActivityScenarioRule<PlayActivity> activityRule = new ActivityScenarioRule<>(PlayActivity.class);

    @Test
    public void testPlayActivity() {
        // test code

        // Click the input box to wait for the list to pop up
        onView(isAssignableFrom(AutoCompleteTextView.class))
                .perform(click())
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()));
        //Wait 1 second
        SystemClock.sleep(1000);

        // Enter n to display the waiting list
        onView(withId(R.id.actvSearch))
                .perform(typeTextIntoFocusedView("n"),// Enter search text
                        closeSoftKeyboard());  // Hidden keyboard
        //Wait 1 second
        SystemClock.sleep(1000);
        // Click the first item in the list
        onData(anything())
                .atPosition(0)
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

        //Wait 10 second
        SystemClock.sleep(1000 * 10);

        //Clear the input box and retype
        onView(withId(R.id.ivClear)).perform(click());
        //Wait 1 second
        SystemClock.sleep(1000);

        onView(withId(R.id.actvSearch))
                .perform(typeTextIntoFocusedView("m"),// Enter search text
                        closeSoftKeyboard());  // Hidden keyboard

        //Wait 1 second
        SystemClock.sleep(1000);
        // Click the first item in the list
        onData(anything())
                .atPosition(0)
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

        //Wait 10 second
        SystemClock.sleep(1000 * 10);

        // Type text and then press the button.
//        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        //Step 1 Play
        Matcher<View> ivPlay = withId(R.id.ivPlay);
        onView(ivPlay).perform(click());

        //Come on in 10 seconds
        SystemClock.sleep(1000 * 10);
        onView(withId(R.id.ivLast)).perform(click());

        //Pause after 10 seconds
        SystemClock.sleep(1000 * 10);
        onView(ivPlay).perform(click());
        //Play in 3 seconds
        SystemClock.sleep(1000 * 3);
        onView(ivPlay).perform(click());
        //Play it again for 10 seconds
        SystemClock.sleep(1000 * 10);
        onView(withId(R.id.ivReplay)).perform(click());
        onView(withText(R.string.str_replay_msg))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        //click cancel after 3 seconds
        SystemClock.sleep(1000 * 3);
        onView(withId(android.R.id.button2)).perform(click());
        //Play it again for 5 seconds
        SystemClock.sleep(1000 * 5);
        onView(withId(R.id.ivReplay)).perform(click());
        onView(withText(R.string.str_replay_msg))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        //click sure after 3 seconds
        SystemClock.sleep(1000 * 3);
        onView(withId(android.R.id.button1)).perform(click());

        //Next track in 10 seconds
        SystemClock.sleep(1000 * 10);
        onView(withId(R.id.ivNext)).perform(click());
    }
}