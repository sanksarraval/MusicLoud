package com.example.musicloud;

import com.example.musicloud.application.MediaPlayerTest;
import com.example.musicloud.business.AccessSongsTest;
import com.example.musicloud.business.AccessUsersTest;
import com.example.musicloud.business.LoginManagerTest;
import com.example.musicloud.business.ValidationInputTest;
import com.example.musicloud.objects.UserTest;
import com.example.musicloud.persistence.UserManagementTest;
import com.example.musicloud.persistence.HSQLDB.SongPersistenceHSQLDBTest;
import com.example.musicloud.persistence.HSQLDB.UserManagementHSQLDBTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        MediaPlayerTest.class,
        UserManagementTest.class,
        SongPersistenceHSQLDBTest.class,

        UserManagementHSQLDBTest.class,

        AccessSongsTest.class,

        AccessUsersTest.class,
        LoginManagerTest.class,
        ValidationInputTest.class,

})
public class AllTests
{




}
