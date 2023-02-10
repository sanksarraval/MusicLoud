package com.example.musicloud;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.musicloud", appContext.getPackageName());
    }

    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<PlayActivity> activityScenarioRule
            = new ActivityScenarioRule<>(PlayActivity.class);

    /**
     * Automated test play, pause, next track, previous track
     */
    @Test
    public void testMusicControl_mainActivity() {
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

        //10 seconds off
        SystemClock.sleep(1000 * 10);
    }
}