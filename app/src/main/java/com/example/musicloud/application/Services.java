package com.example.musicloud.application;

import com.example.musicloud.persistence.PlaylistPersistence;
import com.example.musicloud.persistence.SPPersistence;
import com.example.musicloud.persistence.SongPersistence;
import com.example.musicloud.persistence.UserManagement;
import com.example.musicloud.persistence.hsqldb.PlaylistPersistenceHSQLDB;
import com.example.musicloud.persistence.hsqldb.SPPersistenceHSQLDB;
import com.example.musicloud.persistence.hsqldb.SongPersistenceHSQLDB;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;
import com.example.musicloud.persistence.stubs.SPPersistenceStub;

import java.sql.SQLException;

public class Services {

    private static UserManagement accountManagement = null;
    private static SongPersistence songPersistence = null;
    private static SPPersistence spPersistence = null;
    private static PlaylistPersistence playlistPersistence = null;
    public static synchronized UserManagement getAccountManagement()
    {
        if (accountManagement == null)
        {
            //accountManagement = new UserManagementStub();
            accountManagement = new UserManagementHSQLDB(MyApp.getDBPathName());
        }
        return accountManagement;
    }

    public static synchronized SongPersistence getSongPersistence ()
    {
        if (songPersistence == null)
        {
            //songPersistence = new SongPersistenceStub();
            songPersistence = new SongPersistenceHSQLDB(MyApp.getDBPathName());
        }
        return songPersistence;
    }
    public static synchronized SPPersistence getSpPersistence () {
        if (spPersistence == null)
        {
            spPersistence = new SPPersistenceHSQLDB(MyApp.getDBPathName());
//            spPersistence = new SPPersistenceStub();
        }
        return spPersistence;
    }

    public static synchronized PlaylistPersistence getPlaylistPersistence ()
    {
        if (playlistPersistence == null)
        {
//            playlistPersistence = new PlaylistPersistenceStub();
            playlistPersistence = new PlaylistPersistenceHSQLDB(MyApp.getDBPathName());
        }
        return playlistPersistence;
    }

}
