package com.example.musicloud.application;

import com.example.musicloud.persistence.SongPersistence;
import com.example.musicloud.persistence.UserManagement;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;
import com.example.musicloud.persistence.stubs.SongPersistenceStub;
import com.example.musicloud.persistence.stubs.UserManagementStub;

public class Services {

    private static UserManagement accountManagement = null;

    private static SongPersistence songPersistence = null;
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

}
