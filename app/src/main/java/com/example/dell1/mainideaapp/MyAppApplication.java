package com.example.dell1.mainideaapp;

import android.app.Application;
import android.arch.persistence.room.Room;

public class MyAppApplication extends Application {

    static MyAppDatabase myAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"myDb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static MyAppDatabase getMyAppDatabase(){

        return myAppDatabase;
    }
}
