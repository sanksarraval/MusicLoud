package com.example.musicloud.application;

import com.example.musicloud.persistence.SongPersistence;
import com.example.musicloud.persistence.UserManagement;
import com.example.musicloud.persistence.hsqldb.SongPersistenceHSQLDB;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;
import com.example.musicloud.persistence.stubs.UserManagementStub;

public class Services {

    private static UserManagement accountManagement = null;

    private static SongPersistence songPersistence = null;

    public static synchronized UserManagement getAccountManagement() {
        if (accountManagement == null) {
            accountManagement = new UserManagementHSQLDB(MyApp.getDBPathName());
            //accountManagement = new UserManagementStub();
        }
        return accountManagement;
    }

    public static synchronized SongPersistence getSongPersistence() {
        if (songPersistence == null) {
            //songPersistence = new SongPersistenceStub();
            songPersistence = new SongPersistenceHSQLDB(MyApp.getDBPathName());
        }
        return songPersistence;
    }


}
