package com.example.dell1.mainideaapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@TypeConverters(Converters.class)
@Database(entities = {MyGroups.class,GroupMembers.class},version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract GroupDao getGroupDao();
    public abstract GroupMemberDao getGroupMemberDao();
}                                                                                   
