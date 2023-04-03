package com.example.musicloud.application;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Admin
 */
public class MyApp extends Application {

    /**
     * MyApp variable
     */
    private static MyApp mApp;
    private static String dbName = "SC";

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
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
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
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        copyDatabaseToDevice();
    }

    private void copyDatabaseToDevice() {
        final String dbPath = "db";
        String[] assetNames;
        File dataDirectory = getApplicationContext().getDir(dbPath, Context.MODE_PRIVATE);
        dbName = dataDirectory.getAbsolutePath() + "/SC";
        System.out.println(dbName);
        AssetManager assetManager = getAssets();
        try {
            assetNames = assetManager.list(dbPath);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = dbPath + "/" + assetNames[i];
            }
            copyAssetsToDirectory(assetNames, dataDirectory);
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();
        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;
            File outFile = new File(copyPath);
            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);
                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }
                out.close();
                in.close();
            }
        }
    }

}