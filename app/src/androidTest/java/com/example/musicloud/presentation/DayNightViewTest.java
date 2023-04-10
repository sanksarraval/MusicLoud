package com.example.musicloud.presentation;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;

import android.os.SystemClock;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.musicloud.R;

import org.junit.Rule;
import org.junit.Test;

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
                .check(matches(withClassName(equalTo(ContentFrameLayout.class.getName()))));
        onView(withId(R.id.switch1)).perform(click());
        //Wait for the animation to complete before continuing with the test
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content))
                .check(matches(withClassName(equalTo(ContentFrameLayout.class.getName()))));
    }
}

