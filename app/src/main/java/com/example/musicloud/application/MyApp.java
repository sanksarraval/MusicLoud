package com.example.musicloud.application;

import android.app.Application;

/**
 * @author Admin
 */
public class MyApp extends Application {

    /**
     * MyApp variable
     */
    private static MyApp mApp;
    private static String dbName="SC";

    /**
     * application Global single column
     *
     * @return MyApp
     */
    public static MyApp getInstance() {
        return mApp;
    }
    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }
    public static String getDBPathName() {
        return dbName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize the life cycle
        mApp = this;
    }
}