package com.example.musicloud.application;

import android.app.Application;
import android.content.Context;

/**
 * @author Admin
 */
public class MyApp extends Application {

    /**
     * MyApp variable
     */
    private static MyApp mApp;

    /**
     * application Global single column
     *
     * @return MyApp
     */
    public static MyApp getInstance() {
        return mApp;
    }


    private String dbName;

    public String getDBPathName() {
        return dbName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize the life cycle
        mApp = this;
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = getApplicationContext().getDir("db", Context.MODE_PRIVATE).getAbsolutePath() + "/SC";
        System.out.println(dbName);
    }

}