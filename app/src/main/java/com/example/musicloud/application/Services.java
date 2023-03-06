package com.example.musicloud.application;

import com.example.musicloud.persistence.PlaylistPersistence;
import com.example.musicloud.persistence.SPPersistence;
import com.example.musicloud.persistence.SongPersistence;
import com.example.musicloud.persistence.UserManagement;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;
import com.example.musicloud.persistence.stubs.PlaylistPersistenceStub;
import com.example.musicloud.persistence.stubs.SPPersistenceStub;
import com.example.musicloud.persistence.stubs.SongPersistenceStub;
import com.example.musicloud.persistence.stubs.UserManagementStub;

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
            songPersistence = new SongPersistenceStub();
        }
        return songPersistence;
    }
    public static synchronized SPPersistence getSpPersistence ()
    {
        if (spPersistence == null)
        {
            spPersistence = new SPPersistenceStub();
        }
        return spPersistence;
    }

    public static synchronized PlaylistPersistence getPlaylistPersistence ()
    {
        if (playlistPersistence == null)
        {
            playlistPersistence = new PlaylistPersistenceStub();
        }
        return playlistPersistence;
    }

}
