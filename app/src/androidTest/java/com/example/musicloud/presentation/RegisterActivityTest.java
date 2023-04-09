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
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityRule = new ActivityScenarioRule<>(RegisterActivity.class);

    @Test
    public void testRegisterActivity() {
        // test code

        //Register an existing user
        onView(withId(R.id.full_name_edit_text)).perform(typeText("admin"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard
        onView(withId(R.id.user_name_edit_text)).perform(typeText("admin"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard
        //Wait 1 second
        onView(withId(R.id.password_edit_text)).perform(typeText("admin"),// Enter user password
                closeSoftKeyboard());  // Hidden keyboard
        //Wait 1 second
        onView(withId(R.id.register_btn)).perform(click());

        SystemClock.sleep(200);
        //clear text
        onView(withId(R.id.full_name_edit_text)).perform(clearText());
        onView(withId(R.id.user_name_edit_text)).perform(clearText());
        onView(withId(R.id.password_edit_text)).perform(clearText());

        //Wait 1 second
        SystemClock.sleep(1000);

        onView(withId(R.id.full_name_edit_text)).perform(typeText("new-userId"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard
        onView(withId(R.id.user_name_edit_text)).perform(typeText("new-userName"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard
        //Wait 1 second
        onView(withId(R.id.password_edit_text)).perform(typeText("new-userPassword"),// Enter user password
                closeSoftKeyboard());  // Hidden keyboard

        onView(withId(R.id.full_name_edit_text)).perform(clearText());
        onView(withId(R.id.user_name_edit_text)).perform(clearText());
        onView(withId(R.id.password_edit_text)).perform(clearText());
        SystemClock.sleep(1000);

        //Enter a correct userId and password again
        onView(withId(R.id.full_name_edit_text)).perform(typeText("userId01"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard
        onView(withId(R.id.user_name_edit_text)).perform(typeText("userName01"),// Enter user id
                closeSoftKeyboard());  // Hidden keyboard
        //Wait 1 second
        onView(withId(R.id.password_edit_text)).perform(typeText("userPassword01"),// Enter user password
                closeSoftKeyboard());  // Hidden keyboard

        onView(withId(R.id.register_btn)).perform(click());
        SystemClock.sleep(1000);

    }
}