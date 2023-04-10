package com.example.musicloud;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.musicloud.presentation.DayNightViewTest;
import com.example.musicloud.presentation.LoginActivityTest;
import com.example.musicloud.presentation.PlayActivityTest;
import com.example.musicloud.presentation.RegisterActivityTest;
import com.example.musicloud.presentation.RunPlaylistActivityTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        DayNightViewTest.class,
        LoginActivityTest.class,
        RegisterActivityTest.class,
        PlayActivityTest.class,
        RunPlaylistActivityTest.class,
})


public class AllAcceptanceTests {
    // This class is just a holder for the suite of tests.
    // It does not contain any tests itself.
}
