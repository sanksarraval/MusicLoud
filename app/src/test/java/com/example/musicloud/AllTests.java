package com.example.musicloud;

import com.example.musicloud.application.MediaPlayerTest;
import com.example.musicloud.business.AccessPlaylistTest;
import com.example.musicloud.business.AccessSPTest;
import com.example.musicloud.business.AccessSongs;
import com.example.musicloud.business.AccessSongsTest;
import com.example.musicloud.business.AccessUsersTest;
import com.example.musicloud.business.LoginManagerTest;
import com.example.musicloud.business.ValidationInputTest;
import com.example.musicloud.objects.PlaylistTest;
import com.example.musicloud.objects.SPTest;
import com.example.musicloud.objects.UserTest;
import com.example.musicloud.persistence.HSQLDB.PlaylistPersistenceHSQLDBTest;
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
        PlaylistPersistenceHSQLDBTest.class,
        UserManagementHSQLDBTest.class,
        AccessPlaylistTest.class,
        AccessSongsTest.class,
        AccessSPTest.class,
        AccessUsersTest.class,
        LoginManagerTest.class,
        ValidationInputTest.class,
        PlaylistTest.class,
        SPTest.class
})
public class AllTests
{

}
