package com.srsys.musicloud;

import com.srsys.musicloud.application.MediaPlayerTest;
import com.srsys.musicloud.business.SongListTest;
import com.srsys.musicloud.objects.UserTest;
import com.srsys.musicloud.persistence.UserManagementTest;

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
