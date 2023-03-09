package com.example.musicloud.application;

import com.example.musicloud.persistence.SongPersistence;
import com.example.musicloud.persistence.UserManagement;
import com.example.musicloud.persistence.hsqldb.SongPersistenceHSQLDB;
import com.example.musicloud.persistence.hsqldb.UserManagementHSQLDB;
import com.example.musicloud.persistence.stubs.SongPersistenceStub;
import com.example.musicloud.persistence.stubs.UserManagementStub;

public class Services {

    private static UserManagement accountManagement = null;

    private static SongPersistence songPersistence = null;

    public static synchronized UserManagement getAccountManagement() {
        if (accountManagement == null) {
            accountManagement = new UserManagementHSQLDB(MyApp.getDBPathName()); // Using the HSQLDB Version
            //accountManagement = new UserManagementStub(); To use the stub database, uncomment this line of code and comment the other line.
        }
        return accountManagement;
    }

    public static synchronized SongPersistence getSongPersistence() {
        if (songPersistence == null) {
            //songPersistence = new SongPersistenceStub(); To use the stub database, uncomment this line of code and comment the other line.
            songPersistence = new SongPersistenceHSQLDB(MyApp.getDBPathName()); // Using the HSQLDB Version
        }
        return songPersistence;
    }

}
