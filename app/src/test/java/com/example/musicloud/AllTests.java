package com.example.musicloud;

import com.example.musicloud.application.MediaPlayerTest;
import com.example.musicloud.business.AccessPlaylistTest;
import com.example.musicloud.business.AccessSPTest;
import com.example.musicloud.business.AccessSongsTest;
import com.example.musicloud.objects.PlaylistTest;
import com.example.musicloud.objects.SPTest;
import com.example.musicloud.objects.UserTest;
import com.example.musicloud.persistence.UserManagementTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        MediaPlayerTest.class,
        UserManagementTest.class,
        AccessPlaylistTest.class,
        AccessSongsTest.class,
        AccessSPTest.class,
        PlaylistTest.class,
        SPTest.class
})
public class AllTests
{

}
