package com.example.musicloud.presentation;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.musicloud.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testLoginActivity() {
        // test code

        //Let's start by entering an incorrect user id and password
        onView(withId(R.id.username)).perform(typeText("admin123"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard
        //Wait 1 second
        onView(withId(R.id.Password)).perform(typeText("admin321"),// Enter user password
                closeSoftKeyboard());  // Hidden keyboard
        //Wait 1 second
        onView(withId(R.id.login_btn)).perform(click());

        SystemClock.sleep(200);
        //clear text
        onView(withId(R.id.username)).perform(clearText());
        onView(withId(R.id.Password)).perform(clearText());

        //Wait 1 second
        SystemClock.sleep(1000);


        //Enter a correct userId and password again
        // Enter the user id, in this case, admin
        onView(withId(R.id.username)).perform(typeText("admin"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard

        // Enter the user password, in this case, admin
        onView(withId(R.id.Password)).perform(typeText("admin"),// Enter user password
                closeSoftKeyboard());  // Hidden keyboard

        onView(withId(R.id.login_btn)).perform(click());
        SystemClock.sleep(5000);
    }
}