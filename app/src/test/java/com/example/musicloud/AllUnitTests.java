package com.example.musicloud;

import com.example.musicloud.application.MediaPlayerIntegrationTest;
import com.example.musicloud.application.MediaPlayerTest;
import com.example.musicloud.business.AccessPlaylistTest;
import com.example.musicloud.business.AccessSPTest;
import com.example.musicloud.business.AccessUsersTest;
import com.example.musicloud.business.LoginManagerTest;
import com.example.musicloud.business.ValidationInputTest;
import com.example.musicloud.objects.PlaylistTest;
import com.example.musicloud.objects.SPTest;
import com.example.musicloud.objects.SongTest;
import com.example.musicloud.objects.UserTest;
import com.example.musicloud.persistence.HSQLDB.PlaylistPersistenceHSQLDBTest;
import com.example.musicloud.persistence.HSQLDB.SongPersistenceHSQLDBIntegrationTest;
import com.example.musicloud.persistence.HSQLDB.UserManagementHSQLDBIntegrationTest;
import com.example.musicloud.persistence.UserManagementTest;
import com.example.musicloud.persistence.HSQLDB.SongPersistenceHSQLDBTest;
import com.example.musicloud.persistence.HSQLDB.UserManagementHSQLDBTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessPlaylistTest.class,
        AccessSPTest.class,
        AccessUsersTest.class,
        LoginManagerTest.class,
        ValidationInputTest.class,
        PlaylistTest.class,
        SongTest.class,
        SPTest.class,
        UserTest.class,
        PlaylistPersistenceHSQLDBTest.class,
        SongPersistenceHSQLDBTest.class,
        UserManagementHSQLDBTest.class,
        UserManagementTest.class,
        MediaPlayerIntegrationTest.class,
        SongPersistenceHSQLDBIntegrationTest.class,
        UserManagementHSQLDBIntegrationTest.class
})
public class AllUnitTests
{




}
