package com.example.musicloud;

import com.example.musicloud.application.MediaPlayerIntegrationTest;
import com.example.musicloud.persistence.HSQLDB.SongPersistenceHSQLDBIntegrationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MediaPlayerIntegrationTest.class,
        SongPersistenceHSQLDBIntegrationTest.class
})
public class AllIntegrationTests {


}