package com.example.musicloud.presentation;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.musicloud.presentation.CustomMatchers.withBackgroundColor;

import android.graphics.Color;
import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.musicloud.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DayNightViewTest {
    @Rule
    public ActivityScenarioRule<PlayActivity> activityRule = new ActivityScenarioRule<>(PlayActivity.class);

    @Test
    public void testDayNightView() {
        onView(withId(R.id.switch1)).perform(click());
        // Wait for the animation to complete before continuing with the test
        SystemClock.sleep(3000);
        //onView(withId(android.R.id.content)).check(matches(withBackgroundColor(Color.parseColor("#000000"))));
        onView(withId(android.R.id.content))
                .check(matches(ViewMatchers.withBackgroundColor(Color.parseColor("#000000")))
                .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        //onView(withId(R.id.switch1)).perform(click());
        // Wait for the animation to complete before continuing with the test
        //SystemClock.sleep(2000);
        //onView(withId(android.R.id.content)).check(matches(withBackgroundColor(Color.parseColor("#7f6b97"))));
    }
}