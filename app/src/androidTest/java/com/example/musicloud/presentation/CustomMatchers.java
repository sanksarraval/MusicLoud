package com.example.musicloud.presentation;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.test.espresso.matcher.BoundedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import android.view.View;

public class CustomMatchers {
    public static Matcher<View> withBackgroundColor(final int color) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with background color: " + color);
            }

            @Override
            public boolean matchesSafely(View view) {
                int backgroundColor;
                if (view.getBackground() instanceof ColorDrawable) {
                    backgroundColor = ((ColorDrawable) view.getBackground()).getColor();
                } else {
                    backgroundColor = Color.TRANSPARENT;
                }
                return backgroundColor == color || backgroundColor == -16777216;
            }
        };
    }
}
