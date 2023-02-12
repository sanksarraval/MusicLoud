package com.example.musicloud;

import com.example.musicloud.application.MediaPlayerTest;
import com.example.musicloud.business.SongListTest;
import com.example.musicloud.objects.UserTest;
import com.example.musicloud.persistence.UserManagementTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        MediaPlayerTest.class,
        SongListTest.class,
        UserManagementTest.class
})
public class AllTests
{

}
